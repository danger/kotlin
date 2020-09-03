package systems.danger.cmd.dangerjs

import systems.danger.Logger

interface DangerJSBridge {
    fun process(command: String, processName: String, args: List<String>, logger: Logger)
}