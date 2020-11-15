package systems.danger.kotlin

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import systems.danger.kotlin.sdk.DangerContext
import systems.danger.kotlin.sdk.DangerPlugin
import systems.danger.kotlin.sdk.Violation
import java.io.File

private fun FilePath.readText() = File(this).readText()

object register {
    internal var dangerPlugins = mutableListOf<DangerPlugin>()

    infix fun plugin(plugin: DangerPlugin) {
        dangerPlugins.add(plugin)
    }

    fun plugins(vararg pluginArgs: DangerPlugin) {
        dangerPlugins.addAll(pluginArgs)
    }
}

inline fun register(block: register.() -> Unit) = register.run(block)

inline fun danger(args: Array<String>, block: DangerDSL.() -> Unit) = Danger(args).run(block)

inline fun DangerDSL.onGitHub(onGitHub: GitHub.() -> Unit) {
    if (this.onGitHub) {
        github.run(onGitHub)
    }
}

inline fun DangerDSL.onGitLab(onGitLab: GitLab.() -> Unit) {
    if (this.onGitLab) {
        gitlab.run(onGitLab)
    }
}

inline fun DangerDSL.onBitBucket(onBitBucket: BitBucketServer.() -> Unit) {
    if (this.onBitBucketServer) {
        bitBucketServer.run(onBitBucket)
    }
}

inline fun DangerDSL.onGit(onGit: Git.() -> Unit) {
    git.run(onGit)
}

internal fun DangerPlugin.withContext(dangerContext: DangerContext) {
    context = dangerContext
}

private class DangerRunner(jsonInputFilePath: FilePath, jsonOutputPath: FilePath) : DangerContext {

    val jsonOutputFile: File = File(jsonOutputPath)

    val danger: DangerDSL = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }.decodeFromString<DSL>(jsonInputFilePath.readText()).danger

    val dangerResults: DangerResults = DangerResults()

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

        register.dangerPlugins.forEach {
            it.withContext(this)
        }

        saveDangerResults()
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
        saveDangerResults()
    }

    private fun fail(violation: Violation) {
        dangerResults.fails.add(violation)
        saveDangerResults()
    }

    private fun message(violation: Violation) {
        dangerResults.messages.add(violation)
        saveDangerResults()
    }

    private fun markdown(violation: Violation) {
        dangerResults.markdowns.add(violation)
        saveDangerResults()
    }

    private fun saveDangerResults() {
        val resultsJSON = Json {
            encodeDefaults = true
        }.encodeToString(dangerResults)
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

fun fail(message: String) =
    dangerRunner.fail(message)

fun fail(message: String, file: FilePath, line: Int) =
    dangerRunner.fail(message, file, line)

fun warn(message: String) =
    dangerRunner.warn(message)

fun warn(message: String, file: FilePath, line: Int) =
    dangerRunner.warn(message, file, line)

fun message(message: String) =
    dangerRunner.message(message)

fun message(message: String, file: FilePath, line: Int) =
    dangerRunner.message(message, file, line)

fun markdown(message: String) =
    dangerRunner.markdown(message)

fun markdown(message: String, file: FilePath, line: Int) =
    dangerRunner.markdown(message, file, line)

fun suggest(code: String, file: FilePath, line: Int) =
    dangerRunner.suggest(code, file, line)
