package jp.co.soramitsu.xnetworking.scale.dataType

import com.ionspin.kotlin.bignum.integer.BigInteger
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter

val byteScale by lazy { ByteScaleType() }
val uInt8Scale by lazy { UInt8ScaleType() }
val uInt16Scale by lazy { UInt16ScaleType() }
val uInt32Scale by lazy { UInt32ScaleType() }
val longScale by lazy { LongScaleType() }
val uInt128Scale by lazy { UInt128ScaleType() }
val uInt64Scale by lazy { UInt64ScaleType() }
val compactIntScale by lazy { CompactIntScaleType() }

fun uIntScale(size: Int) = UIntScaleType(size)

expect class ByteScaleType(): ScaleTransformer<Byte> {

    override fun write(scaleWriter: ScaleCodecWriter, value: Byte)

    override fun read(reader: ScaleCodecReader): Byte

    override fun conformsType(value: Any?): Boolean
}

expect class UInt8ScaleType(): ScaleTransformer<UByte> {

    override fun write(scaleWriter: ScaleCodecWriter, value: UByte)

    override fun read(reader: ScaleCodecReader): UByte

    override fun conformsType(value: Any?): Boolean
}

expect class UInt16ScaleType(): ScaleTransformer<Int> {

    override fun write(scaleWriter: ScaleCodecWriter, value: Int)

    override fun read(reader: ScaleCodecReader): Int

    override fun conformsType(value: Any?): Boolean
}

expect class UInt32ScaleType(): ScaleTransformer<UInt> {

    override fun write(scaleWriter: ScaleCodecWriter, value: UInt)

    override fun read(reader: ScaleCodecReader): UInt

    override fun conformsType(value: Any?): Boolean
}

expect class LongScaleType(): ScaleTransformer<Long> {

    override fun write(scaleWriter: ScaleCodecWriter, value: Long)

    override fun read(reader: ScaleCodecReader): Long

    override fun conformsType(value: Any?): Boolean
}

expect open class UIntScaleType(size: Int): ScaleTransformer<BigInteger> {

    override fun write(scaleWriter: ScaleCodecWriter, value: BigInteger)

    override fun read(reader: ScaleCodecReader): BigInteger

    override fun conformsType(value: Any?): Boolean
}

class UInt64ScaleType: UIntScaleType(8)

class UInt128ScaleType: UIntScaleType(16)

expect class CompactIntScaleType() : ScaleTransformer<BigInteger> {

    override fun write(scaleWriter: ScaleCodecWriter, value: BigInteger)

    override fun read(reader: ScaleCodecReader): BigInteger

    override fun conformsType(value: Any?): Boolean
}
