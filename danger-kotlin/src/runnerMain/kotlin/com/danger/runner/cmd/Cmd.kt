package com.danger.runner.cmd

import com.danger.runner.utils.exitIfError
import platform.posix.getpid
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
        exec(true)
    }

    fun exec(printCallLog: Boolean) {
        "$name ${args.joinToString(" ")}".apply {
            if(printCallLog) println("Executing $this - pid ${getpid()}")
        }.also {
            val res = system(it)
            println("$it terminated with result $res - pid ${getpid()}")
            res.exitIfError()
        }
    }
}