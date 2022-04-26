package systems.danger.kotlin.models.serializers

import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalSerializationApi
@Serializer(forClass = DateSerializer::class)
object DateSerializer : KSerializer<Instant> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("kotlinx.datetime.Instant", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Instant) {
        // Implementation not needed for now
    }

    override fun deserialize(decoder: Decoder): Instant {
        val value = decoder.decodeString()

        return try {
            Instant.parse(value)
        } catch(e: Throwable) {
            Instant.fromEpochMilliseconds(ISO8601DateFormat().parse(value).toInstant().toEpochMilli())
        }
    }
}

class ISO8601DateFormat {
    fun parse(string: String?): Date {
        val formatter = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        ).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        return formatter.parse(string.toString())
    }

}
