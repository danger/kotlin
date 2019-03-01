package com.danger.runner.cmd

import com.danger.runner.cmd.kscript.KScriptBridge

object KScript: KScriptBridge {
    private const val KSCRIPT_CMD_NAME = "kscript"
    private const val OPT__IDEA = "--idea"
    private const val OPT__PACKAGE = "--package"

    override val cmd: ICmd
        get() = Cmd

    override fun idea(ktsFile: String) {
        cmd.name(KSCRIPT_CMD_NAME).args(OPT__IDEA, ktsFile).exec()
    }

    override fun pckg(ktsFile: String) {
        cmd.name(KSCRIPT_CMD_NAME).args(OPT__PACKAGE, ktsFile).exec()
    }
}