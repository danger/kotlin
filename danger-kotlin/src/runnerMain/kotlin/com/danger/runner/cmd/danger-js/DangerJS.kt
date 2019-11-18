package com.danger.runner.cmd.`danger-js`

import com.danger.runner.cmd.Cmd

object DangerJS: DangerJSBridge {

    override fun process(command: String, processName: String, args: List<String>) {
        with(Cmd()) {
            name("$(which danger) $command --process $processName --passURLForDSL")
            args(args.joinToString(" "))
            exec()
        }
    }
}