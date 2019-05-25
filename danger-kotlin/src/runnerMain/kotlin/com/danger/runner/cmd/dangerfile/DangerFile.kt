package com.danger.runner.cmd.dangerfile

import com.danger.runner.BuildConfig
import com.danger.runner.cmd.*
import com.danger.runner.cmd.kscript.KScriptBridge

object DangerFile: DangerFileBridge {
    private const val DANGER_FILE = "Dangerfile.kts"

    override val kscript: KScriptBridge
        get() = KScript

    override fun edit() {
        kscript.idea(DANGER_FILE)
    }

    override fun execute(inputJson: String, outputJson: String) {
        Cmd().name("kotlinc").args(
            "-cp",
            "/usr/local/lib/danger/danger-kotlin.jar",
            "-include-runtime",
            "-script",
            DANGER_FILE,
            inputJson,
            outputJson
        )
    }
}