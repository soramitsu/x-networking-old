package jp.co.soramitsu.xnetworking.scale.dataType

import com.ditchoom.buffer.ByteOrder
import com.ionspin.kotlin.bignum.integer.BigInteger
import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.reader.CompactBigIntReader
import jp.co.soramitsu.xnetworking.common.toJavaBigInteger
import jp.co.soramitsu.xnetworking.common.toSharedBigInteger
import jp.co.soramitsu.xnetworking.extensions.fromUnsignedBytes
import jp.co.soramitsu.xnetworking.scale.dataType.utiles.CompactBigIntWriter

actual class ByteScaleType: BaseAndroidScaleTransformer<Byte>(), ScaleTransformer<Byte> {

    override fun read(reader: ScaleCodecReader): Byte {
        return reader.readByte()
    }

    override fun write(writer: ScaleCodecWriter, value: Byte) {
        writer.writeByte(value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is Byte
    }
}

actual class UInt8ScaleType: BaseAndroidScaleTransformer<UByte>(), ScaleTransformer<UByte> {

    override fun read(reader: ScaleCodecReader): UByte {
        return reader.readUByte().toUByte()
    }

    override fun write(writer: ScaleCodecWriter, value: UByte) {
        writer.writeByte(value.toInt())
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is UByte
    }
}

actual class UInt16ScaleType: BaseAndroidScaleTransformer<Int>(), ScaleTransformer<Int> {

    override fun read(reader: ScaleCodecReader): Int {
        return reader.readUint16()
    }

    override fun write(writer: ScaleCodecWriter, value: Int) {
        writer.writeUint16(value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is Int
    }
}

actual class UInt32ScaleType: BaseAndroidScaleTransformer<UInt>(), ScaleTransformer<UInt> {

    override fun read(reader: ScaleCodecReader): UInt {
        return reader.readUint32().toUInt()
    }

    override fun write(writer: ScaleCodecWriter, value: UInt) {
        writer.writeUint32(value.toLong())
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is UInt
    }
}

actual class LongScaleType: BaseAndroidScaleTransformer<Long>(), ScaleTransformer<Long> {

    override fun read(reader: ScaleCodecReader): Long {
        return reader.readLong()
    }

    override fun write(writer: ScaleCodecWriter, value: Long) {
        writer.writeLong(value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is Long
    }
}

actual open class UIntScaleType actual constructor(
    private val size: Int
): BaseAndroidScaleTransformer<BigInteger>(), ScaleTransformer<BigInteger> {

    override fun read(reader: ScaleCodecReader): BigInteger {
        val bytes = reader.readByteArray(size)
        return bytes.fromUnsignedBytes(ByteOrder.LITTLE_ENDIAN)
    }

    override fun write(writer: ScaleCodecWriter, value: BigInteger) {
        val array = value.toByteArray()
        val padded = ByteArray(size)
        val startAt = padded.size - array.size
        array.copyInto(padded, startAt)
        writer.directWrite(padded.reversedArray(), 0, size)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is BigInteger
    }
}

actual class CompactIntScaleType : BaseAndroidScaleTransformer<BigInteger>(), ScaleTransformer<BigInteger> {

    private val compactIntReader = CompactBigIntReader()
    private val compactIntWriter = CompactBigIntWriter()

    override fun read(reader: ScaleCodecReader): BigInteger {
        return compactIntReader.read(reader).toSharedBigInteger()
    }

    override fun write(writer: ScaleCodecWriter, value: BigInteger) {
        compactIntWriter.write(writer, value.toJavaBigInteger())
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is BigInteger
    }
}