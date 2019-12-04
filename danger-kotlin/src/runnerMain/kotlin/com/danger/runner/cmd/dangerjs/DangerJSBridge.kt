package com.danger.runner.cmd.dangerjs

interface DangerJSBridge {
    fun process(command: String, processName: String, args: List<String>)
}