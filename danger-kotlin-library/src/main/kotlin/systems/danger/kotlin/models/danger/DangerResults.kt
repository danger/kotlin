@file:UseSerializers(ViolationSerializer::class)

package systems.danger.kotlin.models.danger

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import systems.danger.kotlin.sdk.Violation
import systems.danger.kotlin.models.serializers.ViolationSerializer
import java.util.concurrent.atomic.AtomicReference

@Serializable
internal data class Meta(
    val runtimeName: String = "Danger Kotlin",
    val runtimeHref: String = "https://danger.systems"
)

@Serializable
internal data class DangerResults(
    val fails: List<Violation>,
    val warnings: List<Violation>,
    val messages: List<Violation>,
    val markdowns: List<Violation>,
    val meta: Meta = Meta()
)

internal data class ConcurrentDangerResults(
    val fails: AtomicReference<MutableList<Violation>> = AtomicReference(mutableListOf()),
    val warnings: AtomicReference<MutableList<Violation>> = AtomicReference(mutableListOf()),
    val messages: AtomicReference<MutableList<Violation>> = AtomicReference(mutableListOf()),
    val markdowns: AtomicReference<MutableList<Violation>> = AtomicReference(mutableListOf())
) {
    fun toDangerResults() = DangerResults(
        fails.get(), warnings.get(), messages.get(), markdowns.get()
    )
}
