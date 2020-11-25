package systems.danger.kotlin

import systems.danger.kotlin.json.JsonParser
import systems.danger.kotlin.models.danger.DSL
import systems.danger.kotlin.models.danger.DangerDSL
import systems.danger.kotlin.models.danger.DangerResults
import systems.danger.kotlin.models.git.FilePath
import systems.danger.kotlin.sdk.DangerContext
import systems.danger.kotlin.sdk.Violation
import java.io.File

internal class MainDangerRunner(jsonInputFilePath: FilePath, jsonOutputPath: FilePath) : DangerContext {

    private val jsonOutputFile: File = File(jsonOutputPath)

    val danger: DangerDSL = JsonParser.decodeJson<DSL>(jsonInputFilePath).danger

    private val dangerResults: DangerResults = DangerResults()

    override val fails: List<Violation>
        get() {
            return dangerResults.fails.toList()
        }
    override val warnings: List<Violation>
        get() {
            return dangerResults.warnings.toList()
        }
    override val messages: List<Violation>
        get() {
            return dangerResults.messages.toList()
        }
    override val markdowns: List<Violation>
        get() {
            return dangerResults.markdowns.toList()
        }

    init {
        // Collect the registered plugins and initialize with the danger context
        register.dangerPlugins.forEach {
            it.withContext(this)
        }
        commit()
    }

    //Api Implementation
    /**
     * Adds an inline fail message to the Danger report
     */
    override fun fail(message: String) {
        fail(Violation(message))
    }

    /**
     * Adds an inline fail message to the Danger report
     */
    override fun fail(message: String, file: FilePath, line: Int) {
        fail(Violation(message, file, line))
    }

    /**
     * Adds an inline warning message to the Danger report
     */
    override fun warn(message: String) {
        warn(Violation(message))
    }

    /**
     * Adds an inline warning message to the Danger report
     */
    override fun warn(message: String, file: FilePath, line: Int) {
        warn(Violation(message, file, line))
    }

    /**
     * Adds an inline message to the Danger report
     */
    override fun message(message: String) {
        message(Violation(message))
    }

    /**
     * Adds an inline message to the Danger report
     */
    override fun message(message: String, file: FilePath, line: Int) {
        message(Violation(message, file, line))
    }

    /**
     * Adds an inline markdown message to the Danger report
     */
    override fun markdown(message: String) {
        markdown(Violation(message))
    }

    /**
     * Adds an inline markdown message to the Danger report
     */
    override fun markdown(message: String, file: FilePath, line: Int) {
        markdown(Violation(message, file, line))
    }

    /**
     * Adds an inline suggest markdown message to the Danger report
     */
    override fun suggest(code: String, file: FilePath, line: Int) {
        if (dangerRunner.danger.onGitHub) {
            val message = "```suggestion\n $code \n```"
            markdown(Violation(message, file, line))
        } else {
            val message = "```\n $code \n```"
            message(Violation(message))
        }
    }

    private fun warn(violation: Violation) {
        dangerResults.warnings.add(violation)
        commit()
    }

    private fun fail(violation: Violation) {
        dangerResults.fails.add(violation)
        commit()
    }

    private fun message(violation: Violation) {
        dangerResults.messages.add(violation)
        commit()
    }

    private fun markdown(violation: Violation) {
        dangerResults.markdowns.add(violation)
        commit()
    }

    private fun commit() {
        JsonParser.encodeJson(dangerResults, jsonOutputFile)
    }
}
