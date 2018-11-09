import java.io.File

private val reader = JSONFilesReader()

val dangerJSON = reader.readJSON("/dangerDSL.json")

private class JSONFilesReader {
    fun readJSON(file: String): String {
        return File("./resources/dangerDSL.json").readText()
    }
}