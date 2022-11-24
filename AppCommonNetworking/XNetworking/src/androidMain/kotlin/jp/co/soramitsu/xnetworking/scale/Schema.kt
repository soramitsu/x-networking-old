package jp.co.soramitsu.xnetworking.scale

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import jp.co.soramitsu.xnetworking.scale.dataType.ScaleTransformer
import java.io.ByteArrayOutputStream

@Suppress("UNCHECKED_CAST")
actual abstract class Schema<S : Schema<S>> :
    BaseSchema<S>(),
    ScaleReader<EncodableStruct<S>>,
    ScaleWriter<EncodableStruct<S>> {

    actual override fun read(bytes: ByteArray): EncodableStruct<S> {
        val reader = ScaleCodecReader(bytes)
        return read(reader)
    }

    override fun read(reader: ScaleCodecReader): EncodableStruct<S> {
        val struct = EncodableStruct(this as S)

        for (field in fields) {
            val value = field.dataType.read(reader)
            struct[field as Field<Any?>] = value
        }

        return struct
    }

    actual  override fun toByteArray(struct: EncodableStruct<S>): ByteArray {
        val outputStream = ByteArrayOutputStream()

        val writer = ScaleCodecWriter(outputStream)

        write(writer, struct)

        return outputStream.toByteArray()
    }

    override fun write(writer: ScaleCodecWriter, struct: EncodableStruct<S>) {
        for (field in fields) {
            val value = struct.fieldsWithValues[field]

            val type = field.dataType as ScaleTransformer<Any?>

            type.write(writer, value)
        }
    }
}
