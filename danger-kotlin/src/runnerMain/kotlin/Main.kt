import systems.danger.DangerKotlin
import systems.danger.cmd.dangerjs.DangerJS

const val PROCESS_DANGER_KOTLIN = "danger-kotlin"

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        when (val command = args.first()) {
            "ci", "local", "pr" -> {
                DangerJS.process(command ,PROCESS_DANGER_KOTLIN, args.drop(1).filterNot { it.startsWith("--addToClassPath") })
            }
            else -> return
        }
    } else {
        val classPaths = args.filter {
            it.startsWith("--addToClassPath=")
        }.map {
            it.replace("--addToClassPath=", "")
        }.toTypedArray()

        DangerKotlin.run(*classPaths)
    }
}