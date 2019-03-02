package com.danger.runner.utils

import platform.posix.remove

inline fun withTempFile(tempFile: String, block: (String) -> Unit) {
    block(tempFile)
    remove(tempFile)
}