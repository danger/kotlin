package systems.danger.kotlin.sdk

/**
 * Danger context
 * Provides the API for writing the Danger results on your Pull Request
 *
 * @constructor Create empty Danger context
 */
interface DangerContext {
    /**
     * Message
     * Adds an inline message message to the Danger report
     *
     * @param message the standard message
     */
    fun message(message: String)

    /**
     * Message
     * Adds an inline message message to the Danger report
     *
     * @param message the standard message
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun message(message: String, file: String, line: Int)

    /**
     * Markdown
     * Adds an inline markdown message to the Danger report
     *
     * @param message the markdown formatted message
     */
    fun markdown(message: String)

    /**
     * Markdown
     * Adds an inline markdown message to the Danger report
     *
     * @param message the markdown formatted message
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun markdown(message: String, file: String, line: Int)

    /**
     * Warn
     * Adds an inline warning message to the Danger report
     *
     * @param message the warning message
     */
    fun warn(message: String)

    /**
     * Warn
     * Adds an inline warning message to the Danger report
     *
     * @param message the warning message
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun warn(message: String, file: String, line: Int)

    /**
     * Fail
     * Adds an inline fail message to the Danger report
     *
     * @param message the fail message
     */
    fun fail(message: String)

    /**
     * Fail
     * Adds an inline fail message to the Danger report
     *
     * @param message the fail message
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun fail(message: String, file: String, line: Int)

    /**
     * Suggest
     * Adds an inline suggested code message to the Danger report
     *
     * @param code the suggested code
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun suggest(code: String, file: String, line: Int)

    val fails: List<Violation>
    val warnings: List<Violation>
    val messages: List<Violation>
    val markdowns: List<Violation>
}

/**
 * Violation
 *
 * @param message the violation message
 * @param file the path to the target file
 * @param line the line number into the target file
 * @constructor Create empty Violation
 */
data class Violation(
    val message: String,
    val file: String? = null,
    val line: Int? = null
)

object Sdk {
    const val VERSION_NAME = "1.1"
    const val API_VERSION = 2
}

/**
 * Danger plugin
 *
 * @constructor Create empty Danger plugin
 */
abstract class DangerPlugin {
    companion object {
        const val DEVELOPED_WITH_API = Sdk.API_VERSION
    }

    /**
     * The plugin id
     */
    abstract val id: String

    lateinit var context: DangerContext
}