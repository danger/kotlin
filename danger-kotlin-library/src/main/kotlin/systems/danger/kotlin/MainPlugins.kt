package systems.danger.kotlin

import systems.danger.kotlin.sdk.DangerContext
import systems.danger.kotlin.sdk.DangerPlugin

/**
 * Register helper
 * example usages:
 * register plugin DangerPluginName
 * register.plugins(DangerPluginName1, DangerPluginName2)
 */
object register {
    internal var dangerPlugins = mutableListOf<DangerPlugin>()

    infix fun plugin(plugin: DangerPlugin) {
        dangerPlugins.add(plugin)
    }

    fun plugins(vararg pluginArgs: DangerPlugin) {
        dangerPlugins.addAll(pluginArgs)
    }
}

inline fun register(block: register.() -> Unit) = register.run(block)

internal fun DangerPlugin.withContext(dangerContext: DangerContext) {
    context = dangerContext
}
