package systems.danger

import systems.danger.cmd.dangerfile.DangerFile

object DangerKotlin {
    private const val FILE_TMP_OUTPUT_JSON = "danger_out.json"

    fun run(logger: Logger) {
        val dangerDSLPath = readLine()

        logger.info("Got Danger DSL path ${dangerDSLPath.stripEndLine()}", true)

        dangerDSLPath?.removePrefix("danger://dsl/")?.stripEndLine()?.let {
            with(DangerFile) {
                execute(it, FILE_TMP_OUTPUT_JSON, logger)
            }

            printResult()
        }
    }

    private fun printResult() {
        println("danger-results:/$FILE_TMP_OUTPUT_JSON")
    }

    private fun String.stripEndLine() = trim('\u007F','\u0001', ' ')
}