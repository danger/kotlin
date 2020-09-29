import platform.posix.getenv
import systems.danger.DangerKotlin
import systems.danger.Log
import systems.danger.cmd.dangerjs.DangerJS

const val PROCESS_DANGER_KOTLIN = "danger-kotlin"
const val VERSION = "0.7.1"

fun main(args: Array<String>) {
    Log.isVerbose = args.contains("--verbose") || (getenv("DEBUG")?.toString()?.isNotEmpty() ?: false)
    Log.info("Starting Danger-Kotlin $VERSION with args '${args.joinToString(", ")}'", verbose = true)

    if (args.isNotEmpty()) {
        when (val command = args.first()) {
            "ci", "local", "pr" -> {
                DangerJS.process(command ,PROCESS_DANGER_KOTLIN, args.drop(1))
            }
            "runner" -> DangerKotlin.run()
            "--version" -> Log.info(VERSION)
            else -> Log.error("Invalid command received: $command")
        }
    } else {
        DangerKotlin.run()
    }
}
