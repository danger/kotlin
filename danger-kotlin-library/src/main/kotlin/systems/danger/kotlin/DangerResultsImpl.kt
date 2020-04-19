package systems.danger.kotlin

data class Meta(
    val runtimeName: String = "Danger Kotlin",
    val runtimeHref: String = "https://danger.systems"
)

internal data class DangerResults(
    var fails: Array<ViolationImpl> = arrayOf(),
    var warnings: Array<ViolationImpl> = arrayOf(),
    var messages: Array<ViolationImpl> = arrayOf(),
    var markdowns: Array<ViolationImpl> = arrayOf(),
    val meta: Meta = Meta()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DangerResults

        if (!fails.contentEquals(other.fails)) return false
        if (!warnings.contentEquals(other.warnings)) return false
        if (!messages.contentEquals(other.messages)) return false
        if (!markdowns.contentEquals(other.markdowns)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fails.contentHashCode()
        result = 31 * result + warnings.contentHashCode()
        result = 31 * result + messages.contentHashCode()
        result = 31 * result + markdowns.contentHashCode()
        return result
    }
}
