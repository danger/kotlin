package systems.danger.kotlin

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.util.*

class UtilsTests {
    private val jsonFiles = JSONFiles()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe()).build().adapter(
        DSL::class.java)
    private val dsl
        get() = moshi.fromJson(jsonFiles.githubDangerJSON)!!

    @Test
    fun testReadText() {
        val testFile = File("testFile")
        testFile.writeText("Test")

        Assert.assertEquals("Test", dsl.danger.utils.readFile("testFile"))

        testFile.delete()
    }
}