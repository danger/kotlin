package systems.danger.kotlin

import java.io.File

class Utils {
    fun readFile(path: String): String {
        return File(path).readText()
    }
}