package systems.danger.kotlin.tools.shell

/**
 * ShellExecutor
 */
interface ShellExecutor {

    /**
     * Execute [command] with optional list of arguments and return output from
     * stdout
     *
     * @param command the shell command
     * @param arguments the list of arguments to be passed to [command]
     * @return the stdout output
     */
    fun execute(command: String, arguments: List<String> = emptyList()): String
}

// Internal implementation for ShellExecutor
internal class ShellExecutorImpl : ShellExecutor {

    override fun execute(command: String, arguments: List<String>): String {
        val commandWithArgs = command + if (arguments.isNotEmpty()) " " + arguments.joinToString(" ") else ""

        val process = Runtime.getRuntime().exec(arrayOf("/bin/bash", "-c", commandWithArgs))
        process.waitFor()

        return process.inputStream.bufferedReader().readText()
    }
}

/**
 * ShellExecutorFactory
 *
 * @constructor Creates a ShellExecutorFactory
 */
object ShellExecutorFactory {

    private var shellExecutor: ShellExecutor = ShellExecutorImpl()

    // Useful for testing
    internal fun set(executor: ShellExecutor) {
        shellExecutor = executor
    }

    /**
     * Get the danger [ShellExecutor]
     */
    fun get() = shellExecutor
}
