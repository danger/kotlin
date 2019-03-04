package com.danger.runner.utils

import platform.posix.exit
import platform.posix.remove

inline fun withTempFile(tempFile: String, block: (String) -> Unit) {
    block(tempFile)
    remove(tempFile)
}

fun Int.exitIfError() {
    if (this != 0)
        exit(this)
}