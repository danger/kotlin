package systems.danger.kotlin

import java.io.File

interface Utils {
    /**
     * Let's you go from a file path to the contents of the file with less hassle.
     *
     * @param path the file reference from git.modified/creasted/deleted etc
     * @return the file contents
     */
    fun readFile(path: String): String

    /**
     * Gives you the ability to cheaply run a command and read the output without having to mess around
     *
     * @param command The first part of the command
     * @param arguments An optional array of arguments to pass in extra
     * @return the stdout from the command
     */
    fun exec(command: String, arguments: List<String> = emptyList()): String
}

internal object UtilsImpl : Utils {

    override fun readFile(path: String): String {
        return File(path).readText()
    }

    override fun exec(command: String, arguments: List<String>): String {
        val commandWithArgs = command + if (arguments.isNotEmpty()) " " + arguments.joinToString(" ") else ""
        return Runtime.getRuntime().exec(arrayOf("/bin/bash", "-c", commandWithArgs)).run {
            waitFor()
            inputStream.bufferedReader().readText()
        }
    }
}
