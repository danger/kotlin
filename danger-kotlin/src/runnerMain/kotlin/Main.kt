import platform.posix.getenv
import systems.danger.DangerKotlin
import systems.danger.Logger
import systems.danger.cmd.dangerjs.DangerJS

const val PROCESS_DANGER_KOTLIN = "danger-kotlin"
const val VERSION = "0.6.1"

fun main(args: Array<String>) {
    val logger = Logger(args.contains("--verbose") || (getenv("DEBUG")?.toString()?.isNotEmpty() ?: false))
    logger.info("Starting Danger-Kotlin $VERSION with args '${args.joinToString(", ")}'", verbose = true)

    if (args.isNotEmpty()) {
        when (val command = args.first()) {
            "ci", "local", "pr" -> {
                DangerJS.process(command ,PROCESS_DANGER_KOTLIN, args.drop(1), logger)
            }
            "runner" -> DangerKotlin.run(logger)
            "--version" -> logger.info(VERSION)
            else -> return
        }
    } else {
        DangerKotlin.run(logger)
    }
}
