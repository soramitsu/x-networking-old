package jp.co.soramitsu.xnetworking.scale.dataType

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import io.emeraldpay.polkaj.scale.writer.BoolWriter
import io.emeraldpay.polkaj.scale.writer.StringWriter
import java.io.ByteArrayOutputStream

abstract class BaseAndroidScaleTransformer<T>:
    ScaleTransformer<T>,
    ScaleWriter<T>,
    ScaleReader<T> {

    override fun encode(value: T): ByteArray {
        val stream = ByteArrayOutputStream()
        val writer = ScaleCodecWriter(stream)
        write(writer, value)
        return stream.toByteArray()
    }

    override fun decode(bytes: ByteArray): T {
        return read(ScaleCodecReader(bytes))
    }
}

actual class StringScaleType: BaseAndroidScaleTransformer<String>(), ScaleTransformer<String> {

    actual override fun conformsType(value: Any?): Boolean {
        return value is String
    }

    override fun read(reader: ScaleCodecReader): String {
        return reader.readString()
    }

    override fun write(writer: ScaleCodecWriter, value: String) {
        return writer.write(StringWriter(), value)
    }
}

actual class BooleanScaleType: BaseAndroidScaleTransformer<Boolean>(), ScaleTransformer<Boolean> {

    actual override fun conformsType(value: Any?): Boolean {
        return value is Boolean
    }

    override fun read(reader: ScaleCodecReader): Boolean {
        return reader.readBoolean()
    }

    override fun write(writer: ScaleCodecWriter, value: Boolean) {
        return writer.write(BoolWriter(), value)
    }
}

actual class ByteArrayScaleType: BaseAndroidScaleTransformer<ByteArray>(), ScaleTransformer<ByteArray> {

    actual override fun conformsType(value: Any?): Boolean {
        return value is ByteArray
    }

    override fun read(reader: ScaleCodecReader): ByteArray {
        return reader.readByteArray()
    }

    override fun write(writer: ScaleCodecWriter, value: ByteArray) {
        return writer.writeByteArray(value)
    }
}

actual class ByteArraySizedScaleType actual constructor(
    private val length: Int
): BaseAndroidScaleTransformer<ByteArray>(), ScaleTransformer<ByteArray> {

    actual override fun conformsType(value: Any?): Boolean {
        return value is ByteArray
    }

    override fun read(reader: ScaleCodecReader): ByteArray {
        return reader.readByteArray(length)
    }

    override fun write(writer: ScaleCodecWriter, value: ByteArray) {
        return writer.directWrite(value, 0, length)
    }
}
