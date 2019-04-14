package com.danger.runner.cmd.dangerfile

import com.danger.runner.cmd.*
import com.danger.runner.cmd.kscript.KScriptBridge
import com.danger.runner.utils.withTempFile

object DangerFile: DangerFileBridge {
    private const val DANGER_FILE_EXECUTABLE = "Dangerfile"
    private const val DANGER_FILE = "$DANGER_FILE_EXECUTABLE.kts"

    override val kscript: KScriptBridge
        get() = KScript

    override fun compile() = apply {
        //kscript.pckg(DANGER_FILE)
    }

    override fun edit() {
        kscript.idea(DANGER_FILE)
    }

    override fun execute(inputJson: String, outputJson: String) {
//        with(Cmd()) {
//            withTempFile(DANGER_FILE_EXECUTABLE) {
//                name("./$it")
//                args(inputJson, outputJson)
//                exec()
//            }
//        }
        Cmd().name("kotlinc")
            .args(
                "-cp",
                "~/.m2/repository/com/danger/danger-kotlin-library/0.1.0/danger-kotlin-library-0.1.0.jar",
                "-include-runtime",
                "-script",
                DANGER_FILE,
                inputJson,
                outputJson
            ).exec()
    }
}