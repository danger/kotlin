package systems.danger.cmd.dangerjs

import systems.danger.Log
import systems.danger.cmd.Cmd
import systems.danger.cmd.Command

object DangerJS: DangerJSBridge {
    override fun process(command: Command, processName: String, args: List<String>) {
        Log.info("Launching Danger-JS", verbose = true)
        with(Cmd()) {
            val dangerJSArgumentIndex = args.indexOf("--danger-js-path")
            val dangerJSPath: String
            if (dangerJSArgumentIndex != -1 && args.count() > dangerJSArgumentIndex + 1) {
                dangerJSPath = args[dangerJSArgumentIndex + 1]
            } else {
                dangerJSPath = "$(which danger)"
            }
            name("$dangerJSPath ${command.argument} --process $processName --passURLForDSL")
            args(args.joinToString(" "))
            exec()
        }
    }
}

