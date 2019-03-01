package com.danger.runner.cmd.kscript

import com.danger.runner.cmd.ICmd

interface KScriptBridge {
    val cmd: ICmd
    fun idea(ktsFileName: String)
    fun pckg(ktsFileName: String)
}