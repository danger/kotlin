package com.danger.runner.cmd

interface ICmd {
    fun name(name: String): ICmd
    fun args(vararg args: String): ICmd
    fun exec()
}