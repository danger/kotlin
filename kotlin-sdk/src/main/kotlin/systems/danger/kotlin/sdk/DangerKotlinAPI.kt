package systems.danger.kotlin.sdk

import com.danger.dangerkotlin.*

interface DangerKotlinAPI {
    fun message(message: String)
    fun message(message: String, file: String, line: Int)
    fun markdown(message: String)
    fun markdown(message: String, file: String, line: Int)
    fun warn(message: String)
    fun warn(message: String, file: String, line: Int)
    fun fail(message: String)
    fun fail(message: String, file: String, line: Int)
    fun suggest(code: String, file: String, line: Int)
}

class DangerKotlinApiGetter {
    companion object {
        fun get() = DangerKotlinAPIProvider.getApi()
    }
}