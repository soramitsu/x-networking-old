package jp.co.soramitsu.xnetworking.runtime.definitions.types.generics

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger
import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.runtime.definitions.types.primitives.Primitive
import jp.co.soramitsu.xnetworking.scale.dataType.compactIntScale
import jp.co.soramitsu.xnetworking.scale.dataType.uIntScale
import kotlin.math.ceil

typealias Bits = BooleanArray

object BitVec : Primitive<Bits>("BitVec") {

    private val TWO = BigInteger.parseString("2")

    override fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): Bits {
        val sizeInBits = compactIntScale.read(scaleCodecReader).intValue(exactRequired = true)
        val sizeInBytes = sizeInBytes(sizeInBits)

        val bits = BooleanArray(sizeInBits)

        if (sizeInBits == 0) return booleanArrayOf()

        val bitsHolder = uIntScale(sizeInBytes).read(scaleCodecReader)

        repeat(sizeInBits) { i ->
            val mask = TWO.pow(i)

            bits[i] = bitsHolder.and(mask).isPositive
        }

        return bits
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, runtime: RuntimeSnapshot, value: Bits) {
        val intValue = value.foldIndexed(BigInteger.ZERO) { index, acc, bit ->
            if (bit) {
                acc + TWO.pow(index)
            } else {
                acc
            }
        }

        val sizeInBytes = sizeInBytes(value.size)

        compactIntScale.write(scaleCodecWriter, value.size.toBigInteger())

        if (value.isNotEmpty()) {
            uIntScale(sizeInBytes).write(scaleCodecWriter, intValue)
        }
    }

    override fun isValidInstance(instance: Any?): Boolean {
        return instance is List<*> && instance.all { it is Boolean }
    }

    private fun sizeInBytes(inBits: Int) = ceil(inBits / 8.0).toInt()
}
