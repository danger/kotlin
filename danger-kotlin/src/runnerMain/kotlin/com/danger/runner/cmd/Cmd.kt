package com.danger.runner.cmd

import platform.posix.system

object Cmd : ICmd {
    private const val DEFAULT_NAME = ""
    private val DEFAULT_ARGS = arrayOf<String>()

    private var name: String = DEFAULT_NAME
    private var args: Array<out String> = DEFAULT_ARGS

    override fun name(name: String) = apply {
        this.name = name
    }

    override fun args(vararg args: String) = apply {
        this.args = args
    }

    override fun exec() {
        "$name ${args.joinToString(" ")}".apply {
            println("Executing $this...")
        }.also {
            system(it)
            resetToDefaults()
        }
    }

    private fun resetToDefaults() {
        name = DEFAULT_NAME
        args = DEFAULT_ARGS
    }
}