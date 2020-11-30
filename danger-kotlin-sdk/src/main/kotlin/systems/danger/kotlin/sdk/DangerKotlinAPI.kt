package systems.danger.kotlin.sdk

/**
 * Defines the API to post the Danger results on your Pull Request
 *
 * @constructor Create empty Danger context
 */
interface DangerContext {

    /**
     * Adds an inline message message to the Danger report
     *
     * @param message the standard message
     */
    fun message(message: String)

    /**
     * Adds an inline message message to the Danger report
     *
     * @param message the standard message
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun message(message: String, file: String, line: Int)

    /**
     * Adds an inline markdown message to the Danger report
     *
     * @param message the markdown formatted message
     */
    fun markdown(message: String)

    /**
     * Adds an inline markdown message to the Danger report
     *
     * @param message the markdown formatted message
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun markdown(message: String, file: String, line: Int)

    /**
     * Adds an inline warning message to the Danger report
     *
     * @param message the warning message
     */
    fun warn(message: String)

    /**
     * Adds an inline warning message to the Danger report
     *
     * @param message the warning message
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun warn(message: String, file: String, line: Int)

    /**
     * Adds an inline fail message to the Danger report
     *
     * @param message the fail message
     */
    fun fail(message: String)

    /**
     * Adds an inline fail message to the Danger report
     *
     * @param message the fail message
     * @param file the path to the target file
     * @param line the line number into the target file
     */
    fun fail(message: String, file: String, line: Int)

    /**
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
 * Violation is any comment on your Pull Request
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

/**
 * Describe the Sdk, contains:
 * - [Sdk.API_VERSION]
 * - [Sdk.VERSION_NAME]
 *
 * @constructor Create empty Sdk
 */
object Sdk {
    const val VERSION_NAME = "1.1"
    const val API_VERSION = 2
}

/**
 * A DangerPlugin is a special object that contains utils methods to be executed into the Danger File.
 * To create a new plugin you need to extend this class as per example:
 * ```
 * object MyDangerPlugin: DangerPlugin() {
 *     override val id = "MyUniquePluginId"
 *
 *     fun myUtilMethod() {
 *         context.warn("This is a util method")
 *     }
 * }
 * ```
 * your plugin can be registered and executed into your DangerFile with:
 * ```
 * register plugin MyDangerPlugin
 * ```
 * and then used:
 * ```
 * danger(args) {
 *     MyDangerPlugin.myUtilMethod()
 * }
 * ```
 * with this example a warning message is published on your Pull Request.
 *
 * @constructor Create empty Danger plugin
 */
abstract class DangerPlugin {
    companion object {
        const val DEVELOPED_WITH_API = Sdk.API_VERSION
    }

    // The plugin unique id
    abstract val id: String

    // The DangerContext
    lateinit var context: DangerContext
}