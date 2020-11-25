package systems.danger.kotlin

import systems.danger.kotlin.models.danger.DangerDSL
import systems.danger.kotlin.models.git.FilePath

internal lateinit var dangerRunner: MainDangerRunner

inline fun danger(args: Array<String>, block: DangerDSL.() -> Unit) = Danger(args).run(block)

fun Danger(args: Array<String>): DangerDSL {
    val argsCount = args.count()

    val jsonInputFilePath = args[argsCount - 2]
    val jsonOutputPath = args[argsCount - 1]

    dangerRunner = MainDangerRunner(jsonInputFilePath, jsonOutputPath)

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
