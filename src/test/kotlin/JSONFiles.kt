import java.io.File

private val reader = JSONFilesReader()

val dangerJSON = reader.readJSON("./resources/dangerDSL.json")

private class JSONFilesReader {
    fun readJSON(file: String): String {
        return File(file).readText()
    }
}