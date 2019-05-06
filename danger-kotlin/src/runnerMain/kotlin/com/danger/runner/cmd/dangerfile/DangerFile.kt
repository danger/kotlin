package com.danger.runner.cmd.dangerfile

import com.danger.runner.BuildConfig
import com.danger.runner.cmd.*
import com.danger.runner.cmd.kscript.KScriptBridge

object DangerFile: DangerFileBridge {
    private const val DANGER_FILE_EXECUTABLE = "Dangerfile"
    private const val DANGER_FILE = "$DANGER_FILE_EXECUTABLE.kts"

    override val kscript: KScriptBridge
        get() = KScript

    override fun edit() {
        kscript.idea(DANGER_FILE)
    }

    override fun execute(inputJson: String, outputJson: String) {
        Cmd().name("MVN_HOME=\$(mvn help:evaluate -Dexpression=settings.localRepository -q -DforceStdout) && kotlinc")
            .args(
                "-cp",
                "\$MVN_HOME/${BuildConfig.groupIdDangerKotlinLibrary.replace(".", "/")}/${BuildConfig.versionDangerKotlinLibrary}/${BuildConfig.artifactIdDangerKotlin}-${BuildConfig.versionDangerKotlinLibrary}.jar" +
                        ":\$MVN_HOME/${BuildConfig.groupIdMoshi.replace(".","/")}/${BuildConfig.artifactIdMoshi}/${BuildConfig.versionMoshi}/${BuildConfig.artifactIdMoshi}-${BuildConfig.versionMoshi}.jar" +
                        ":\$MVN_HOME/${BuildConfig.groupIdMoshi.replace(".","/")}/${BuildConfig.artifactIdMoshiKotlin}/${BuildConfig.versionMoshi}/${BuildConfig.artifactIdMoshiKotlin}-${BuildConfig.versionMoshi}.jar" +
                        ":\$MVN_HOME/${BuildConfig.groupIdOkio.replace(".", "/")}/${BuildConfig.artifactIdOkio}/${BuildConfig.versionOkio}/${BuildConfig.artifactIdOkio}-${BuildConfig.versionOkio}.jar",
                "-include-runtime",
                "-script",
                DANGER_FILE,
                inputJson,
                outputJson
            ).exec()
    }
}