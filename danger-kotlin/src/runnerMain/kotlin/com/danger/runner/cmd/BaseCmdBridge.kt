package com.danger.runner.cmd

abstract class BaseCmdBridge {
    private lateinit var cmd: ICmd
    fun withCmd(cmd: ICmd) {
        this.cmd = cmd
    }
}