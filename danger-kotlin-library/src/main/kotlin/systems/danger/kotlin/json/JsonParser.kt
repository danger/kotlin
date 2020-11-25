package systems.danger.kotlin.json

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import systems.danger.kotlin.models.git.FilePath
import java.io.File

// internal Json parser to decode and encode jsons from/to danger-js
internal object JsonParser {

    private val decodeJsonParser = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val encodeJsonParser = Json {
        encodeDefaults = true
    }

    inline fun <reified T> decodeJson(path: FilePath): T {
        return decodeJsonParser.decodeFromString(path.readText())
    }

    inline fun <reified T> encodeJson(value: T, outputFile: File) {
        val jsonString = encodeJsonParser.encodeToString(value)
        outputFile.writeText(jsonString)
    }

    private fun FilePath.readText() = File(this).readText()
}
