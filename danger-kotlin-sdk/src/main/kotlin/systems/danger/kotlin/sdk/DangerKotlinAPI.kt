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

    val dangerResults: DangerResults
}

interface Violation {
    val message: String
    val file: String?
    val line: Int?
}

data class Meta(
        val runtimeName: String = "Danger Kotlin",
        val runtimeHref: String = "https://danger.systems"
)

interface DangerResults {
    val fails: Array<Violation>
    val warnings: Array<Violation>
    val messages: Array<Violation>
    val markdowns: Array<Violation>
    val meta: Meta
}

object Sdk {
    const val VERSION_NAME = "1.1"
    const val API_VERSION = 2
}

abstract class DangerPlugin {
    companion object {
        const val DEVELOPED_WITH_API = Sdk.API_VERSION
    }

    abstract val id: String
    lateinit var context: DangerContext
}