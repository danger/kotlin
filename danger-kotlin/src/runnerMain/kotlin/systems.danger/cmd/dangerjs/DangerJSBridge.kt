package systems.danger.cmd.dangerjs

interface DangerJSBridge {
    fun process(command: String, processName: String, args: List<String>)
}
