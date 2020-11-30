package systems.danger.kotlin

import kotlinx.serialization.decodeFromString
import org.junit.Assert
import org.junit.Test
import systems.danger.kotlin.models.danger.DSL
import systems.danger.kotlin.utils.TestUtils.JSONFiles
import systems.danger.kotlin.utils.TestUtils
import java.io.File

class UtilsTests {
    private val dsl: DSL = TestUtils.Json.decodeFromString(JSONFiles.githubDangerJSON)

    @Test
    fun testReadText() {
        val testFile = File("testFile")
        testFile.writeText("Test")

        Assert.assertEquals("Test", dsl.danger.utils.readFile("testFile"))

        testFile.delete()
    }
}