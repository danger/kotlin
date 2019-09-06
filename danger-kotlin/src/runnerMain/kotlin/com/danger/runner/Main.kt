package com.danger.runner

import com.danger.runner.cmd.`danger-js`.DangerJS
import com.danger.runner.cmd.dangerfile.DangerFile

const val PROCESS_DANGER_KOTLIN = "danger-kotlin"

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        when (val command = args.first()) {
            "ci", "local", "pr" -> {
                DangerJS.process(command ,PROCESS_DANGER_KOTLIN, args.drop(1))
            }
            else -> return
        }
    } else {
        DangerKotlin.run()
    }
}