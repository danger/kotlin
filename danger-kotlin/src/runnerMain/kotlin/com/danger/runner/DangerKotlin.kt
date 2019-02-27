package com.danger.runner

import platform.posix.*


fun main(args: Array<String>) {
    if (args.size > 0) {
        val command = args.first()
        when (command) {
            "ci", "local", "pr" -> {
                val dangerArgs = args.drop(1)
                runDangerJS(command, dangerArgs)
            }
            "edit" -> runEditCommand()
            else -> return
        }
    } else {
        runDangerKotlin()
    }
}

fun String.exec() = system(this)