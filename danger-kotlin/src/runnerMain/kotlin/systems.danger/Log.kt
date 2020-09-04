package systems.danger

import kotlin.native.concurrent.ThreadLocal

private object Color {
    private const val escape = '\u001B'
    const val default = "$escape[0;0m"
    const val red = "$escape[31m"
    const val yellow = "$escape[33m"
}

@ThreadLocal
object Log {

    var isVerbose: Boolean = false

    fun info(message: String, verbose: Boolean = false) {
        printMessage(message, Color.default, verbose)
    }

    fun warning(message: String, verbose: Boolean = false) {
        printMessage("WARNING: $message", Color.yellow, verbose)
    }

    fun error(message: String, verbose: Boolean = false) {
        printMessage("ERROR: $message", Color.red, verbose)
    }

    private fun printMessage(message: String, color: String, verbose: Boolean) {
        if (!verbose || (verbose && this.isVerbose)) {
            println(color + message + Color.default)
        }
    }
}
