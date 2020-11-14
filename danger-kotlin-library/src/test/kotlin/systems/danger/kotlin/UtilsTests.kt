package systems.danger.kotlin

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test
import java.io.File

class UtilsTests {
    private val jsonFiles = JSONFiles()
    private val dsl: DSL
        get() = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }.decodeFromString(jsonFiles.githubDangerJSON)

    @Test
    fun testReadText() {
        val testFile = File("testFile")
        testFile.writeText("Test")

        Assert.assertEquals("Test", dsl.danger.utils.readFile("testFile"))

        testFile.delete()
    }
}