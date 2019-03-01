package com.danger.runner.cmd.`danger-js`

interface DangerJSBridge {
    fun process(command: String, processName: String, args: List<String>)
}