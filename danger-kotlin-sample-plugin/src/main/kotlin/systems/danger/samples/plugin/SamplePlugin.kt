package systems.danger.samples.plugin

import systems.danger.kotlin.sdk.DangerPlugin

object SamplePlugin : DangerPlugin() {
    override val id: String
        get() = "systems.danger.kotlin.samplePlugin"

    fun myCustomCheck() {
        context.message("Verified custom plugins")
    }
}