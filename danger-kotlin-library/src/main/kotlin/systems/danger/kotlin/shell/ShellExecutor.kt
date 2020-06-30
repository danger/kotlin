package systems.danger.kotlin.shell

interface ShellExecutor {

    /**
     * Execute [command] with optional list of arguments and return output from
     * stdout
     */
    fun execute(command: String, arguments: List<String> = emptyList()): String
}

class ShellExecutorImpl : ShellExecutor {

    override fun execute(command: String, arguments: List<String>): String {
        val commandWithArgs = command + if (arguments.isNotEmpty()) " " + arguments.joinToString(" ") else ""

        val process = Runtime.getRuntime().exec(arrayOf("/bin/bash", "-c", commandWithArgs))
        process.waitFor()

        return process.inputStream.bufferedReader().readText()
    }
}

object ShellExecutorFactory {

    private var shellExecutor: ShellExecutor = ShellExecutorImpl()

    /**
     * Useful for testing
     */
    internal fun set(executor: ShellExecutor) {
        this.shellExecutor = executor
    }

    fun get() = shellExecutor
}