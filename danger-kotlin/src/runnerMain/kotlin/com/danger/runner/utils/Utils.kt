package com.danger.runner.utils

import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fputs

fun stdinToFile(fileName: String) {
    fopen(fileName, "wt")?.apply {
        do {
            val line = readLine()?.let {
                fputs(it, this)
            }
        } while (line != null)
    }.also {
        fclose(it)
    }
}