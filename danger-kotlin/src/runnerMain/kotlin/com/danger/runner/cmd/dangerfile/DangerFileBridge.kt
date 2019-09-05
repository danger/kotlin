package com.danger.runner.cmd.dangerfile

interface DangerFileBridge {
    fun edit()
    fun execute(inputJson: String, outputJson: String)
}