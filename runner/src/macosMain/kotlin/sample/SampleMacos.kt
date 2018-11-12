package sample

import platform.posix.*

const val TMP_INPUT_JSON_FILE = "danger_in.json"

const val TMP_OUT_JSON_FILE = "/Users/zuddag01/Repository/danger-kotlin/runner/build/bin/macos/main/release/executable/danger_out.json"

fun main(args: Array<String>) {

    fopen(TMP_INPUT_JSON_FILE, "wt")?.apply {
        do {
            val line = readLine()?.let {
                fputs(it, this)
            }
        } while (line != null)
    }.also {
        fclose(it)
    }

    "kscript --package DangerFile.kts".exec()
    "./DangerFile $TMP_INPUT_JSON_FILE $TMP_OUT_JSON_FILE".exec()

    //"kscript DangerFile.kts $TMP_INPUT_JSON_FILE $TMP_OUT_JSON_FILE".exec()
    println("danger-results:/$TMP_OUT_JSON_FILE")
}

fun String.exec() = system(this)