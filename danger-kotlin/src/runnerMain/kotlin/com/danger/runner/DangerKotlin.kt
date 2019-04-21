package com.danger.runner

import com.danger.config.BuildConfig
import com.danger.runner.cmd.*
import com.danger.runner.cmd.dangerfile.DangerFile
import com.danger.runner.utils.stdinToFile
import com.danger.runner.utils.withTempFile

object DangerKotlin {
    private const val FILE_TMP_INPUT_JSON = "danger_in.json"
    private const val FILE_TMP_OUTPUT_JSON = "danger_out.json"

    fun run() {
        withTempFile(FILE_TMP_INPUT_JSON) { inputJson ->
            stdinToFile(inputJson)
            with(DangerFile) {
                execute(inputJson, FILE_TMP_OUTPUT_JSON)
            }
        }

        printResult()
    }

    private fun printResult() {
        //TODO: find proper way to print the result in the stdout
        Cmd().name("echo").args("danger-results:/`pwd`/$FILE_TMP_OUTPUT_JSON").exec(false)
    }
}