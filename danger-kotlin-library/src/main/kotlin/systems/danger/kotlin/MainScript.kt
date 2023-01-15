package systems.danger.kotlin

import systems.danger.kotlin.models.danger.DangerDSL
import systems.danger.kotlin.models.git.FilePath
import systems.danger.kotlin.sdk.Violation

internal var dangerRunner: MainDangerRunner? = null

internal val runnerInstance: MainDangerRunner
    get() {
        if(dangerRunner != null) {
            return dangerRunner!!
        } else {
            throw IllegalArgumentException("Danger must be initialised before accessing it")
        }
    }

/**
 * Syntactic sugar that allows you to work with a [DangerDSL] descriptor in a single Danger block.
 * Example code:
 * ```
 * danger(args) {
 *     ...
 * }
 * ```
 *
 * @param args the DangerFile arguments, is always just args
 * @param block the [DangerDSL] block
 * @receiver the [DangerDSL] descriptor
 */
inline fun danger(args: Array<String>, block: DangerDSL.() -> Unit) = Danger(args).run(block)

/**
 * Create and return a [DangerDSL] descriptor
 * Example code:
 * ```
 * val danger = Danger(args)
 * ```
 *
 * @param args
 * @return a new [DangerDSL] descriptor
 */
fun Danger(args: Array<String>): DangerDSL {
    if (dangerRunner == null) {
        val argsCount = args.count()

        val jsonInputFilePath = args[argsCount - 2]
        val jsonOutputPath = args[argsCount - 1]

        dangerRunner = MainDangerRunner(jsonInputFilePath, jsonOutputPath)
    }

    return dangerRunner!!.danger
}

/**
 * Adds an inline message message to the Danger report
 *
 * @param message the standard message
 */
fun message(message: String) =
    runnerInstance.message(message)

/**
 * Adds an inline message message to the Danger report
 *
 * @param message the standard message
 * @param file the path to the target file
 * @param line the line number into the target file
 */
fun message(message: String, file: FilePath, line: Int) =
    runnerInstance.message(message, file, line)

/**
 * Adds an inline markdown message to the Danger report
 *
 * @param message the markdown formatted message
 */
fun markdown(message: String) =
    runnerInstance.markdown(message)

/**
 * Adds an inline markdown message to the Danger report
 *
 * @param message the markdown formatted message
 * @param file the path to the target file
 * @param line the line number into the target file
 */
fun markdown(message: String, file: FilePath, line: Int) =
    runnerInstance.markdown(message, file, line)

/**
 * Adds an inline warning message to the Danger report
 *
 * @param message the warning message
 */
fun warn(message: String) =
    runnerInstance.warn(message)

/**
 * Adds an inline warning message to the Danger report
 *
 * @param message the warning message
 * @param file the path to the target file
 * @param line the line number into the target file
 */
fun warn(message: String, file: FilePath, line: Int) =
    runnerInstance.warn(message, file, line)

/**
 * Adds an inline fail message to the Danger report
 *
 * @param message the fail message
 */
fun fail(message: String) =
    runnerInstance.fail(message)

/**
 * Adds an inline fail message to the Danger report
 *
 * @param message the fail message
 * @param file the path to the target file
 * @param line the line number into the target file
 */
fun fail(message: String, file: FilePath, line: Int) =
    runnerInstance.fail(message, file, line)

/**
 * Adds an inline suggested code message to the Danger report
 *
 * @param code the suggested code
 * @param file the path to the target file
 * @param line the line number into the target file
 */
fun suggest(code: String, file: FilePath, line: Int) =
    runnerInstance.suggest(code, file, line)

/** Fails on the Danger report */
val fails: List<Violation>
    get() = runnerInstance.fails.toList()

/** Warnings on the Danger report */
val warnings: List<Violation>
    get() = runnerInstance.warnings.toList()

/** Messages on the Danger report */
val messages: List<Violation>
    get() = runnerInstance.messages.toList()

/** Markdowns on the Danger report */
val markdowns: List<Violation>
    get() = runnerInstance.markdowns.toList()
