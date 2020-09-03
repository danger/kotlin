package systems.danger.cmd.dangerfile

import systems.danger.Logger

interface DangerFileBridge {
    fun execute(inputJson: String, outputJson: String, logger: Logger)
}