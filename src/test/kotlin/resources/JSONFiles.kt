package resources

private val reader = JSONReader()
val dangerDSLJSON = reader.readJSON("dangerDSL.json")


class JSONReader {
    fun readJSON(file: String): String {
        return this.javaClass::class.java.getResource(file).readText()
    }
}