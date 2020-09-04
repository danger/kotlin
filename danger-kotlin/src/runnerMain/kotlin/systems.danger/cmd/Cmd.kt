package systems.danger.cmd

import platform.posix.*
import systems.danger.Log

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

    private fun exec(printCallLog: Boolean) {
        "$name ${args.joinToString(" ")}".apply {
            if (printCallLog) {
                Log.info("Executing $this - pid ${getpid()}")
            }
        }.also {
            val exitCode = system(it)

            if (exitCode != 0) {
                throw Exception("Command $it exited with code $exitCode")
            }
        }
    }
}
