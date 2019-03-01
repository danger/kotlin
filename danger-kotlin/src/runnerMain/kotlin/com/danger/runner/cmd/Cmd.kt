package com.danger.runner.cmd

import platform.posix.system

class Cmd {
    private lateinit var name: String
    private lateinit var args: Array<out String>

    fun name(name: String) = apply {
        this.name = name
    }

    fun args(vararg args: String) = apply {
        this.args = args
    }

    fun exec() {
        "$name ${args.joinToString(" ")}".apply {
            println("Executing $this...")
        }.also {
            system(it)
        }
    }
}