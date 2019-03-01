package com.danger.runner.cmd.`danger-js`

import com.danger.runner.cmd.Cmd
import com.danger.runner.cmd.ICmd

object DangerJS: DangerJSBridge {
    override val cmd: ICmd
        get() = Cmd

    override fun process(command: String, processName: String, args: List<String>) {
        with(cmd) {
            name("$(which danger-$command)")
            args(args.joinToString(" "))
            exec()
        }
    }
}