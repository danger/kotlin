package systems.danger

import systems.danger.cmd.dangerfile.DangerFile

object DangerKotlin {
    private const val FILE_TMP_OUTPUT_JSON = "danger_out.json"

    fun run() {
        val dangerDSLPath = readLine()

        if (dangerDSLPath != null) {
            Log.info("Got Danger DSL path $dangerDSLPath", true)
        } else {
            Log.error("Didn't receive a DSL path")
        }

        dangerDSLPath?.removePrefix("danger://dsl/")?.stripEndLine()?.let {
            Log.info("Stripped DSL Path $it", true)
            with(DangerFile) {
                execute(it, FILE_TMP_OUTPUT_JSON)
            }

            printResult()
        }
    }

    private fun printResult() {
        println("danger-results:/$FILE_TMP_OUTPUT_JSON")
    }

    private fun String.stripEndLine(): String {
        return replaceAfter(".json", "")
    }
}
