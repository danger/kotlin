package systems.danger.kotlin

import java.io.File

class Utils {
    fun readFile(path: String): String {
        return File(path).readText()
    }

    fun exec(command: String, arguments: Array<String> = arrayOf()): String {
        var commandToExec = "/bin/bash -c $command" + if (arguments.isNotEmpty()) "" else " " + arguments.joinToString(" ")

        val process = Runtime.getRuntime().exec(commandToExec)
        process.waitFor()

        return process.inputStream.bufferedReader().readText()
    }
}