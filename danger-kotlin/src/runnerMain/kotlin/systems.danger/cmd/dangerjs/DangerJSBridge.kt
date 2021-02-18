package systems.danger.cmd.dangerjs

import systems.danger.cmd.Command

interface DangerJSBridge {
    fun process(command: Command, processName: String, args: List<String>)
}
