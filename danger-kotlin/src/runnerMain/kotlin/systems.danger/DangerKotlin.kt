package systems.danger

import systems.danger.cmd.dangerfile.DangerFile

object DangerKotlin {
    private const val FILE_TMP_OUTPUT_JSON = "danger_out.json"

    fun run() {
        val dangerDSLPath = readLine()
        dangerDSLPath?.removePrefix("danger://dsl/")?.let {
            with(DangerFile) {
                execute(it, FILE_TMP_OUTPUT_JSON)
            }

            printResult()
        }
    }

    private fun printResult() {
        println("danger-results:/$FILE_TMP_OUTPUT_JSON")
    }
}