package com.danger.dangerkotlin.sdk

import com.danger.dangerkotlin.DangerKotlinAPIProvider

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
        @JvmStatic
        fun get() = DangerKotlinAPIProvider.getApi()
    }
}