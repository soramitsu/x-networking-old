package jp.co.soramitsu.xnetworking.runtime.definitions.types.composite

import com.ionspin.kotlin.bignum.integer.toBigInteger
import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.runtime.definitions.types.TypeReference
import jp.co.soramitsu.xnetworking.scale.dataType.compactIntScale

class Vec(name: String, typeReference: TypeReference) : WrapperType<List<*>>(name, typeReference) {

    override fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): List<*> {
        val type = typeReference.requireValue()
        val size = compactIntScale.read(scaleCodecReader)
        val result = mutableListOf<Any?>()

        repeat(size.intValue(exactRequired = true)) {
            val element = type.decode(scaleCodecReader, runtime)
            result.add(element)
        }

        return result
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, runtime: RuntimeSnapshot, value: List<*>) {
        val type = typeReference.requireValue()
        val size = value.size.toBigInteger()
        compactIntScale.write(scaleCodecWriter, size)

        value.forEach {
            type.encodeUnsafe(scaleCodecWriter, runtime, it)
        }
    }

    override fun isValidInstance(instance: Any?): Boolean {
        return instance is List<*> && instance.all {
            typeReference.requireValue().isValidInstance(it)
        }
    }
}
