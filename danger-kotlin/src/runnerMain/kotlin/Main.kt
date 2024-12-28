import platform.posix.getenv
import systems.danger.DangerKotlin
import systems.danger.Log
import systems.danger.cmd.Command
import systems.danger.cmd.dangerjs.DangerJS

const val PROCESS_DANGER_KOTLIN = "danger-kotlin"
const val VERSION = "1.3.3"

fun main(args: Array<String>) {
    Log.isVerbose = args.contains("--verbose") || (getenv("DEBUG")?.toString()?.isNotEmpty() ?: false)
    Log.info("Starting Danger-Kotlin $VERSION with args '${args.joinToString(", ")}'", verbose = true)

    if (args.isNotEmpty()) {
        when {
            args.contains("--help") -> {
                Log.info("danger-kotlin [command]")
                Log.info("")
                Log.info("Commands:")
                Log.info(Command.values().joinToString("\n") { it.argument + "\t" + it.description })
            }
            args.contains("--version") -> {
                Log.info(VERSION)
            }
            else -> {
                when (val command = Command.forArgument(args.first())) {
                    Command.CI, Command.LOCAL, Command.PR -> {
                        DangerJS.process(command, PROCESS_DANGER_KOTLIN, args.drop(1).map { if(it.contains(" ")) "'$it'" else it })
                    }
                    Command.RUNNER -> DangerKotlin.run()
                    else -> Log.error("Invalid command received: $command")
                }
            }
        }
    } else {
        DangerKotlin.run()
    }
}
