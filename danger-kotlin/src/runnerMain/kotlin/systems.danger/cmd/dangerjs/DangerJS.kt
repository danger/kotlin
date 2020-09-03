package systems.danger.cmd.dangerjs

import systems.danger.Logger
import systems.danger.cmd.Cmd

object DangerJS: DangerJSBridge {

    override fun process(command: String, processName: String, args: List<String>, logger: Logger) {
        logger.info("Launching Danger-JS", verbose = true)
        with(Cmd(logger)) {
            name("$(which danger) $command --process $processName --passURLForDSL")
            args(args.joinToString(" "))
            exec()
        }
    }
}