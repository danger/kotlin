package systems.danger.kotlin.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import systems.danger.kotlin.sdk.Violation

@ExperimentalSerializationApi
@Serializer(forClass = ViolationSerializer::class)
object ViolationSerializer : KSerializer<Violation> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor(
            "systems.danger.kotlin.sdk.Violation"
        ) {
            element<String>("message")
            element<String>("file")
            element<Int>("line")
        }

    override fun deserialize(decoder: Decoder): Violation {
        // Do not need implementation (we are only serialising
        return decoder.decodeStructure(descriptor) {
            lateinit var message: String
            var file: String? = null
            var line: Int? = null
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> message = decodeStringElement(descriptor, 0)
                    1 -> file = decodeStringElement(descriptor, 1)
                    2 -> line = decodeIntElement(descriptor, 2)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            Violation(message, file, line)
        }
    }

    override fun serialize(encoder: Encoder, value: Violation) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.message)
            value.file?.let {
                encodeStringElement(descriptor, 1, it)
            }
            value.line?.let {
                encodeIntElement(descriptor, 2, it)
            }
        }
    }

}
