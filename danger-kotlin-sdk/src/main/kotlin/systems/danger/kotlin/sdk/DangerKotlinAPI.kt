package systems.danger.kotlin.sdk

interface DangerContext {
    fun message(message: String)
    fun message(message: String, file: String, line: Int)
    fun markdown(message: String)
    fun markdown(message: String, file: String, line: Int)
    fun warn(message: String)
    fun warn(message: String, file: String, line: Int)
    fun fail(message: String)
    fun fail(message: String, file: String, line: Int)
    fun suggest(code: String, file: String, line: Int)
}

object Sdk {
    const val VERSION_NAME = "1.0"
    const val API_VERSION = 1
}

abstract class DangerPlugin {
    companion object {
        const val DEVELOPED_WITH_API = Sdk.API_VERSION
    }

    abstract val id: String
    lateinit var context: DangerContext
}