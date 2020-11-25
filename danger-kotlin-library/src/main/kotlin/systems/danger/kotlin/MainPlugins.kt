package systems.danger.kotlin

import systems.danger.kotlin.sdk.DangerContext
import systems.danger.kotlin.sdk.DangerPlugin

/**
 * Register
 * Helps to register a [DangerPlugin] before usage.
 * contains the plugins to be registered within [DangerContext]
 *
 * @constructor Create empty Register helper
 */
object register {
    internal var dangerPlugins = mutableListOf<DangerPlugin>()

    /**
     * Plugin
     * Example code:
     * ```
     * register plugin DangerPluginName
     *
     * // Then
     *
     * danger(args) {
     * ...
     * }
     * ```
     *
     * @param plugin the [DangerPlugin] to be registered
     */
    infix fun plugin(plugin: DangerPlugin) {
        dangerPlugins.add(plugin)
    }

    /**
     * Plugins
     * Example code:
     * ```
     * register.plugins(DangerPluginName1, DangerPluginName2, ...)
     *
     * // Then
     *
     * danger(args) {
     * ...
     * }
     * ```
     *
     * @param pluginArgs the [DangerPlugin]s
     */
    fun plugins(vararg pluginArgs: DangerPlugin) {
        dangerPlugins.addAll(pluginArgs)
    }
}

/**
 * Register
 * Block that gave another option for registering plugins
 * Example code:
 * ```
 * register {
 *     plugin(DangerPlugin1)
 *     plugin(DangerPlugin2)
 *     plugins(DangerPlugin3, DangerPlugin4)
 * }
 *
 * // Then
 *
 * danger(args) {
 * ...
 * }
 * ```
 *
 * @param block
 * @receiver [register] the registered plugins container
 */
inline fun register(block: register.() -> Unit) = register.run(block)

/**
 * With context
 * internal util function that assign the [DangerContext] to a target [DangerPlugin]
 *
 * @param dangerContext the [DangerContext]
 */
internal fun DangerPlugin.withContext(dangerContext: DangerContext) {
    context = dangerContext
}
