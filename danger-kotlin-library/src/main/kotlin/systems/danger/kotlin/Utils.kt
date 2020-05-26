package systems.danger.kotlin

import java.io.File

class Utils {

    fun readFile(path: String): String {
        return File(path).readText()
    }

    /**
     * Gives you the ability to cheaply run a command and read the output without having to mess around
     *
     * @param command The first part of the command
     * @param arguments An optional array of arguements to pass in extra
     * @return the stdout from the command
     */
    fun exec(command: String, arguments: Array<String> = arrayOf()): String {
        val commandToExec = "/bin/bash -c $command" + if (arguments.isNotEmpty())  " " + arguments.joinToString(" ") else ""

        val process = Runtime.getRuntime().exec(commandToExec)
        process.waitFor()

        return process.inputStream.bufferedReader().readText()
    }
}