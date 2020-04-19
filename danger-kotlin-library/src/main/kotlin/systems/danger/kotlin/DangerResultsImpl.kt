package systems.danger.kotlin

import systems.danger.kotlin.sdk.DangerResults
import systems.danger.kotlin.sdk.Meta
import systems.danger.kotlin.sdk.Violation

internal data class DangerResultsImpl(
        override var fails: Array<Violation> = arrayOf(),
        override var warnings: Array<Violation> = arrayOf(),
        override var messages: Array<Violation> = arrayOf(),
        override var markdowns: Array<Violation> = arrayOf(),
        override val meta: Meta = Meta()
): DangerResults {
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
