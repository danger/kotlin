package systems.danger.cmd.dangerjs

import systems.danger.Log
import systems.danger.cmd.Cmd

object DangerJS: DangerJSBridge {
    override fun process(command: String, processName: String, args: List<String>) {
        Log.info("Launching Danger-JS", verbose = true)
        with(Cmd()) {
            val dangerJSArgumentIndex = args.indexOf("--danger-js-path")
            val dangerJSPath: String
            if (dangerJSArgumentIndex == -1 && args.count() > dangerJSArgumentIndex + 1) {
                dangerJSPath = "$(which danger)"
            } else {
                dangerJSPath = args[dangerJSArgumentIndex + 1]
            }
            name("$dangerJSPath $command --process $processName --passURLForDSL")
            args(args.joinToString(" "))
            exec()
        }
    }
}
