package systems.danger.cmd

enum class Command(val argument: String) {
    CI("ci"),
    LOCAL("local"),
    PR("pr"),
    RUNNER("runner");

    companion object {
        fun forArgument(argument: String) = values().find{ it.argument == argument }
    }

    val description: String
        get() {
            when (this) {
                Command.CI -> {
                    return "Use this on CI"
                }
                Command.LOCAL -> {
                    return "Use this to run danger against your local changes from master/main"
                }
                Command.PR -> {
                    return "Run danger-swift locally against a PR"
                }
                Command.RUNNER -> {
                    return "Triggers the Dangerfile evaluation (used mainly by DangerJS)"
                }
            }
        }
}