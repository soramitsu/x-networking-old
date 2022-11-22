package jp.co.soramitsu.xnetworking.scale.dataType

import com.ionspin.kotlin.bignum.integer.BigInteger

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

    override fun encode(value: Byte): ByteArray

    override fun decode(bytes: ByteArray): Byte

    override fun conformsType(value: Any?): Boolean
}

expect class UInt8ScaleType(): ScaleTransformer<UByte> {

    override fun encode(value: UByte): ByteArray

    override fun decode(bytes: ByteArray): UByte

    override fun conformsType(value: Any?): Boolean
}

expect class UInt16ScaleType(): ScaleTransformer<Int> {

    override fun encode(value: Int): ByteArray

    override fun decode(bytes: ByteArray): Int

    override fun conformsType(value: Any?): Boolean
}

expect class UInt32ScaleType(): ScaleTransformer<UInt> {

    override fun encode(value: UInt): ByteArray

    override fun decode(bytes: ByteArray): UInt

    override fun conformsType(value: Any?): Boolean
}

expect class LongScaleType(): ScaleTransformer<Long> {

    override fun encode(value: Long): ByteArray

    override fun decode(bytes: ByteArray): Long

    override fun conformsType(value: Any?): Boolean
}

expect open class UIntScaleType(size: Int): ScaleTransformer<BigInteger> {

    override fun encode(value: BigInteger): ByteArray

    override fun decode(bytes: ByteArray): BigInteger

    override fun conformsType(value: Any?): Boolean
}

class UInt64ScaleType: UIntScaleType(8)

class UInt128ScaleType: UIntScaleType(16)

expect class CompactIntScaleType() : ScaleTransformer<BigInteger> {

    override fun encode(value: BigInteger): ByteArray

    override fun decode(bytes: ByteArray): BigInteger

    override fun conformsType(value: Any?): Boolean
}
