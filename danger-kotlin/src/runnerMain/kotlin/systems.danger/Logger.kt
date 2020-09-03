package systems.danger

private object Color {
    private const val escape = '\u001B'
    const val default = "$escape[0;0m"
    const val red = "$escape[31m"
    const val yellow = "$escape[33m"
}

class Logger {
    private val verbose: Boolean

    constructor(verbose: Boolean) {
        this.verbose = verbose
    }

    fun info(message: String, verbose: Boolean = false) {
        if (!verbose || (verbose && this.verbose)) {
            printMessage(message, Color.default)
        }
    }

    fun warning(message: String, verbose: Boolean = false) {
        if (!verbose || (verbose && this.verbose)) {
            printMessage("WARNING: $message", Color.yellow)
        }
    }

    fun error(message: String, verbose: Boolean = false) {
        if (!verbose || (verbose && this.verbose)) {
            printMessage("ERROR: $message", Color.red)
        }
    }

    private fun printMessage(message: String, color: String) {
        print(color + message + Color.default + "\n")
    }
}