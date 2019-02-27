package com.danger.runner
import platform.posix.*

fun runDangerJS(command: String, args: List<String>) {
    val argsString = args.joinToString(" ")
    val dangerJSCall = "$(which danger-$command) --process danger-kotlin $argsString"

    "echo \"Executing $dangerJSCall\"".exec()

    dangerJSCall.exec()
}