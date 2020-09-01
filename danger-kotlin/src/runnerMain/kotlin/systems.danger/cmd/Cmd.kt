package systems.danger.cmd

import platform.posix.*

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
        "/bin/bash -c \"$name ${args.joinToString(" ")}\"".apply {
            if(printCallLog) {
                println("Executing $this - pid ${getpid()}")
            }
        }.also {
            val exitCode = system(it)

            if(exitCode != 0) {
                throw Exception("Command $it exited with code $exitCode")
            }
        }
    }
}