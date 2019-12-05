import com.danger.runner.DangerKotlin
import com.danger.runner.cmd.dangerjs.DangerJS

const val PROCESS_DANGER_KOTLIN = "danger-kotlin"

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        when (val command = args.first()) {
            "ci", "local", "pr" -> {
                DangerJS.process(command ,PROCESS_DANGER_KOTLIN, args.drop(1))
            }
            else -> return
        }
    } else {
        DangerKotlin.run()
    }
}