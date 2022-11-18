package jp.co.soramitsu.xnetworking.scale.dataType

interface ScaleEncoder<T> {
    fun encode(value: T): ByteArray
}

interface ScaleDecoder<T> {
    fun decode(bytes: ByteArray): T
}

interface StringScaleTransformer<T> : ScaleEncoder<T>, ScaleDecoder<T> {
    override fun encode(value: T): ByteArray
    override fun decode(bytes: ByteArray): T
    fun conformsType(value: Any?): Boolean
}

val stringScale by lazy { StringScaleType() }

expect class StringScaleType(): StringScaleTransformer<String> {
    override fun encode(value: String): ByteArray
    override fun decode(bytes: ByteArray): String
    override fun conformsType(value: Any?): Boolean
}

/*object string : DataType<String>() {
    override fun read(reader: ScaleCodecReader): String {
        return reader.readString()
    }
    override fun write(writer: ScaleCodecWriter, value: String) = writer.writeString(value)

    override fun conformsType(value: Any?) = value is String
}*/

/*
object boolean : DataType<Boolean>() {
    override fun read(reader: ScaleCodecReader): Boolean {
        return reader.readBoolean()
    }

    override fun write(writer: ScaleCodecWriter, value: Boolean) = writer.write(BoolWriter(), value)

    override fun conformsType(value: Any?) = value is Boolean
}

object byteArray : DataType<ByteArray>() {
    override fun read(reader: ScaleCodecReader): ByteArray {
        val readByteArray = reader.readByteArray()
        return readByteArray
    }

    override fun write(writer: ScaleCodecWriter, value: ByteArray) {
        writer.writeByteArray(value)
    }

    override fun conformsType(value: Any?) = value is ByteArray
}

class byteArraySized(private val length: Int) : DataType<ByteArray>() {
    override fun read(reader: ScaleCodecReader): ByteArray {
        val readByteArray = reader.readByteArray(length)
        return readByteArray
    }

    override fun write(writer: ScaleCodecWriter, value: ByteArray) = writer.directWrite(value, 0, length)

    override fun conformsType(value: Any?) = value is ByteArray
}
*/
