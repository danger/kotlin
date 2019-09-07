package com.danger.runner.cmd.dangerfile

import com.danger.runner.cmd.*

object DangerFile: DangerFileBridge {
    private const val DANGER_FILE = "Dangerfile.main.kts"

    override fun execute(inputJson: String, outputJson: String) {
        Cmd().name("$(which kotlinc)").args(
            inputJson,
            outputJson,
            "-cp",
            "/usr/local/lib/danger/danger-kotlin.jar:/usr/local/lib/danger/kotlin-main-kts.jar",
            "-script",
            DANGER_FILE
        ).exec()
    }
}