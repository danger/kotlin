package com.danger.dangerkotlin

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun toISO8601UTC(date: Date): String {
    val tz = TimeZone.getTimeZone("UTC")
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    df.timeZone = tz
    return df.format(date)
}

fun fromISO8601UTC(dateStr: String): Date? {
    val tz = TimeZone.getTimeZone("UTC")
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    df.timeZone = tz

    try {
        return df.parse(dateStr)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return null
}

class Rfc3339DateJsonAdapter: JsonAdapter<Date>() {
    @Synchronized
    override fun fromJson(reader: JsonReader): Date {
        val string = reader.nextString()
        return fromISO8601UTC(string)!!
    }

    override fun toJson(writer: JsonWriter, value: Date?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private class DangerRunner(jsonInputFilePath: FilePath, jsonOutputPath: FilePath) {
    val jsonOutputFile: File = File(jsonOutputPath)
    val danger: DangerDSL
    val dangerResults: DangerResults = DangerResults(arrayOf(), arrayOf(),arrayOf(), arrayOf())

    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .add(KotlinJsonAdapterFactory())
        .build()

    init {
        this.danger = moshi.adapter(DSL::class.java).fromJson(jsonInputFilePath.readText())!!.danger
        saveDangerResults()
    }

    fun saveDangerResults() {
        val resultsJSON = moshi.adapter(DangerResults::class.java).toJson(dangerResults)
        jsonOutputFile.writeText(resultsJSON)
    }
}

private lateinit var dangerRunner: DangerRunner

fun Danger(args: Array<String>): DangerDSL {
    val argsCount = args.count()

    val jsonInputFilePath = args[argsCount - 2]
    val jsonOutputPath = args[argsCount - 1]

    dangerRunner = DangerRunner(jsonInputFilePath, jsonOutputPath)
    return dangerRunner.danger
}

/**
* Adds an inline fail message to the Danger report
*/
fun fail(message: String) {
    fail(Violation(message))
}

/**
* Adds an inline fail message to the Danger report
*/
fun fail(message: String, file: FilePath, line: Int) {
    fail(Violation(message, file, line))
}

private fun fail(violation: Violation) {
    dangerRunner.dangerResults.fails+=violation
    dangerRunner.saveDangerResults()
}

/**
* Adds an inline warning message to the Danger report
*/
fun warn(message: String) {
    warn(Violation(message))
}

/**
* Adds an inline warning message to the Danger report
*/
fun warn(message: String, file: FilePath, line: Int) {
    warn(Violation(message, file, line))
}

private fun warn(violation: Violation) {
    dangerRunner.dangerResults.warnings+=(violation)
    dangerRunner.saveDangerResults()
}

/**
* Adds an inline message to the Danger report
*/
fun message(message: String) {
    message(Violation(message))
}

/**
* Adds an inline message to the Danger report
*/
fun message(message: String, file: FilePath, line: Int) {
    message(Violation(message, file, line))
}

private fun message(violation: Violation) {
    dangerRunner.dangerResults.messages+=violation
    dangerRunner.saveDangerResults()
}

/**
* Adds an inline markdown message to the Danger report
*/
fun markdown(message: String) {
    markdown(Violation(message))
}

/**
* Adds an inline markdown message to the Danger report
*/
fun markdown(message: String, file: FilePath, line: Int) {
    markdown(Violation(message, file, line))
}

private fun markdown(violation: Violation) {
    dangerRunner.dangerResults.markdowns+=violation
    dangerRunner.saveDangerResults()
}


/**
 * Adds an inline suggest markdown message to the Danger report
 */
fun suggest(code: String, file: FilePath, line: Int) {
    if (dangerRunner.danger.onGitHub) {
        val message = "```suggestion\n $code \n```"
        markdown(Violation(message, file, line))
    } else {
        val message = "```\n $code \n```"
        message(Violation(message))
    }
}

private fun FilePath.readText() = File(this).readText()

