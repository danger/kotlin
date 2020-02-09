package systems.danger.cmd.dangerfile

interface DangerFileBridge {
    fun execute(inputJson: String, outputJson: String, vararg addToClassPath: String?)
}