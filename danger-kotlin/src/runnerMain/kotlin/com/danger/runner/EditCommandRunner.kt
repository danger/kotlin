package com.danger.runner
import platform.posix.*

fun runEditCommand() {
    "kscript --idea Dangerfile.kts".exec()
}