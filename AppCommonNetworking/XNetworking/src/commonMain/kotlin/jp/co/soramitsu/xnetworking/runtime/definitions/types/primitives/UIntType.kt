package jp.co.soramitsu.xnetworking.runtime.definitions.types.primitives

import com.ionspin.kotlin.bignum.integer.BigInteger
import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.scale.dataType.uIntScale

val u8 = UIntType(8)
val u16 = UIntType(16)
val u32 = UIntType(32)
val u64 = UIntType(64)
val u128 = UIntType(128)
val u256 = UIntType(256)

class UIntType(bits: Int) : NumberType("u$bits") {

    init {
        require(bits % 8 == 0)
    }

    val bytes = bits / 8

    private val codec = uIntScale(size = bytes)

    override fun encode(scaleCodecWriter: ScaleCodecWriter, runtime: RuntimeSnapshot, value: BigInteger) {
        codec.write(scaleCodecWriter, value)
    }

    override fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): BigInteger {
        return codec.read(scaleCodecReader)
    }
}
