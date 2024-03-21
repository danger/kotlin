package systems.danger.cmd.dangerfile

import systems.danger.cmd.*
import kotlinx.cinterop.CPointer
import platform.posix.*
import systems.danger.Log

object DangerFile : DangerFileBridge {
    private const val DANGERFILE_EXTENSION = ".df.kts"
    private const val DANGERFILE = "Dangerfile$DANGERFILE_EXTENSION"
    private val platformExpectedLibLocations = setOf(
        "/usr/local", // x86 location
        "/opt/local", // Arm
        "/opt/homebrew", // Homebrew Arm
        "/usr", // Fallback
    )

    override fun execute(inputJson: String, outputJson: String) {
        val dangerKotlinJarPath = platformExpectedLibLocations
            .map { "$it/lib/danger/danger-kotlin.jar" }
            .filter { access(it, F_OK) == 0 }
            .also {
                if (it.isEmpty()) {
                    Log.error("lib/danger/danger-kotlin.jar not found in following location ${platformExpectedLibLocations.joinToString()}")
                    exit(1)
                }
            }.first()

        val dangerfile = dangerfileParameter(inputJson) ?: DANGERFILE

        if (!dangerfile.endsWith(DANGERFILE_EXTENSION)) {
            Log.error("The Dangerfile is not valid, it must have '$DANGERFILE_EXTENSION' as extension")
            exit(1)
        }

        Log.info("Compiling Dangerfile $dangerfile", true)

        Cmd().name("kotlinc").args(
            "-script-templates",
            "systems.danger.kts.DangerFileScript",
            "-cp",
            dangerKotlinJarPath,
            "-script",
            dangerfile,
            inputJson,
            outputJson
        ).exec()
    }
}

private fun dangerfileParameter(inputJson: String): String? {
    var result: String? = null

    fopen(inputJson, "r")?.apply {
        do {
            val line = readLine(this)?.let {
                val trimmedLine = it.trim()
                if (trimmedLine.startsWith("\"dangerfile\":")) {
                    val dangerFile =
                        trimmedLine.removePrefix("\"dangerfile\": \"").removeSuffix("\"").removeSuffix("\",")
                    result = dangerFile
                }
            }
        } while (line != null && result == null)
    }.also {
        fclose(it)
    }

    return result
}

private fun readLine(file: CPointer<FILE>): String? {
    var ch = getc(file)
    var lineBuffer: Array<Char> = arrayOf()

    while ((ch != '\n'.code) && (ch != EOF)) {
        lineBuffer += ch.toChar()

        ch = getc(file)
    }

    return if (lineBuffer.isEmpty()) {
        null
    } else {
        lineBuffer.joinToString("")
    }
}
