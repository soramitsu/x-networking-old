package jp.co.soramitsu.xnetworking.runtime.definitions.types.composite

import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.runtime.definitions.types.ScaleCodecWriters
import jp.co.soramitsu.xnetworking.runtime.definitions.types.TypeReference
import jp.co.soramitsu.xnetworking.runtime.definitions.types.errors.EncodeDecodeException
import jp.co.soramitsu.xnetworking.runtime.definitions.types.primitives.BooleanType

class Option(
    name: String,
    typeReference: TypeReference
) : WrapperType<Any?>(name, typeReference) {

    override fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): Any? {
        if (typeReference.requireValue() is BooleanType) {
            return when (scaleCodecReader.readByte().toInt()) {
                0 -> null
                1 -> false
                2 -> true
                else -> throw EncodeDecodeException("Not a optional boolean")
            }
        }

        val some: Boolean = scaleCodecReader.readBoolean()

        return if (some) typeReference.requireValue().decode(scaleCodecReader, runtime) else null
    }

    override fun encode(scaleCodecWriter: ScaleCodecWriter, runtime: RuntimeSnapshot, value: Any?) {
        val type = typeReference.requireValue()

        if (type is BooleanType) {
            scaleCodecWriter.writeOptional(ScaleCodecWriters.BOOL, value as Boolean)
        } else {
            if (value == null) {
                scaleCodecWriter.write(ScaleCodecWriters.BOOL, false)
            } else {
                scaleCodecWriter.write(ScaleCodecWriters.BOOL, true)
                type.encodeUnsafe(scaleCodecWriter, runtime, value)
            }
        }
    }

    override fun isValidInstance(instance: Any?): Boolean {
        return instance == null || typeReference.requireValue().isValidInstance(instance)
    }
}
