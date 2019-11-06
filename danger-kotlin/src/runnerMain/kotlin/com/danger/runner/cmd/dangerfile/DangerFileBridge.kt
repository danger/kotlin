package com.danger.runner.cmd.dangerfile

interface DangerFileBridge {
    fun execute(inputJson: String, outputJson: String)
}