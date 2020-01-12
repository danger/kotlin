package systems.danger.cmd.dangerjs

import systems.danger.cmd.Cmd

object DangerJS: DangerJSBridge {

    override fun process(command: String, processName: String, args: List<String>) {
        with(Cmd()) {
            name("$(which danger) $command --process $processName --passURLForDSL")
            args(args.joinToString(" "))
            exec()
        }
    }
}