package jp.co.soramitsu.xnetworking.runtime.definitions.types.composite

import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.runtime.definitions.types.TypeReference

class FixedArray(name: String, val length: Int, typeReference: TypeReference) :
    WrapperType<List<*>>(name, typeReference) {

    override fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): List<*> {
        val type = typeReference.requireValue()
        val list = mutableListOf<Any?>()

        repeat(length) {
            list += type.decode(scaleCodecReader, runtime)
        }

        return list
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, runtime: RuntimeSnapshot, value: List<*>) {
        val type = typeReference.requireValue()

        value.forEach {
            type.encodeUnsafe(scaleCodecWriter, runtime, it)
        }
    }

    override fun isValidInstance(instance: Any?): Boolean {
        val type = typeReference.requireValue()

        return instance is List<*> && instance.size == length && instance.all(type::isValidInstance)
    }
}
