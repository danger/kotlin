package com.danger.runner.cmd.kscript

interface KScriptBridge {
    fun idea(ktsFile: String)
    fun pckg(ktsFile: String)
}