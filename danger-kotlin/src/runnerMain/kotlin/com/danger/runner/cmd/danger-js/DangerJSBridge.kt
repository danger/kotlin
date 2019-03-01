package com.danger.runner.cmd.`danger-js`

import com.danger.runner.cmd.ICmd

interface DangerJSBridge {
    val cmd: ICmd
    fun process(command: String, processName: String, args: List<String>)
}