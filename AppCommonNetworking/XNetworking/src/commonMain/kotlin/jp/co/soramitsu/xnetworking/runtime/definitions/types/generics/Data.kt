package jp.co.soramitsu.xnetworking.runtime.definitions.types.generics

import jp.co.soramitsu.xnetworking.runtime.RuntimeSnapshot
import jp.co.soramitsu.xnetworking.runtime.definitions.registry.TypePresetBuilder
import jp.co.soramitsu.xnetworking.runtime.definitions.registry.getOrCreate
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.runtime.definitions.types.TypeReference
import jp.co.soramitsu.xnetworking.runtime.definitions.types.composite.DictEnum
import jp.co.soramitsu.xnetworking.runtime.definitions.types.directWrite
import jp.co.soramitsu.xnetworking.runtime.definitions.types.errors.EncodeDecodeException
import jp.co.soramitsu.xnetworking.scale.dataType.byteScale

class Data(preset: TypePresetBuilder) : DictEnum(TYPE_NAME, createMapping(preset)) {

    companion object {
        const val NONE = "None"
        const val RAW = "Raw"
        const val BLAKE_2B_256 = "BlakeTwo256"
        const val SHA_256 = "Sha256"
        const val KECCAK_256 = "Keccak256"
        const val SHA_3_256 = "ShaThree256"

        const val TYPE_NAME = "Data"
    }

    override fun decode(scaleCodecReader: ScaleCodecReader, runtime: RuntimeSnapshot): Entry<Any?> {

        return when (val typeIndex = byteScale.read(scaleCodecReader).toInt()) {
            0 -> Entry(NONE, null)

            in 1..33 -> {
                val offset = 1
                val data = scaleCodecReader.readByteArray(typeIndex - offset)

                Entry(RAW, data)
            }

            in 34..37 -> {
                val offset = 32
                val variantIndex = typeIndex - offset

                val typeEntry = elements[variantIndex] ?: elementNotFound(variantIndex)

                val decoded = typeEntry.value.requireValue().decode(scaleCodecReader, runtime)

                Entry(typeEntry.name, decoded)
            }

            else -> throw EncodeDecodeException("Cannot process $typeIndex for Data type")
        }
    }

    override fun encode(
        scaleCodecWriter: ScaleCodecWriter,
        runtime: RuntimeSnapshot,
        value: Entry<Any?>
    ) {
        when (value.name) {
            NONE -> byteScale.write(scaleCodecWriter, 0)
            RAW -> {
                val raw = value.value
                require(raw is ByteArray && raw.size in 0..32)
                val index = raw.size + 1

                byteScale.write(scaleCodecWriter, index.toByte())
                scaleCodecWriter.directWrite(raw)
            }
            else -> {
                val offset = 32
                val entry = entryOf(value.name)

                val type = entry.value.value.requireValue()

                scaleCodecWriter.writeByte(entry.key + offset)
                type.encodeUnsafe(scaleCodecWriter, runtime, value.value)
            }
        }
    }
}

private fun createMapping(preset: TypePresetBuilder): List<DictEnum.Entry<TypeReference>> {
    return listOf(
        DictEnum.Entry(Data.NONE, preset.getOrCreate("Null")),
        DictEnum.Entry(Data.RAW, preset.getOrCreate("Bytes")),
        DictEnum.Entry(Data.BLAKE_2B_256, preset.getOrCreate("H256")),
        DictEnum.Entry(Data.SHA_256, preset.getOrCreate("H256")),
        DictEnum.Entry(Data.KECCAK_256, preset.getOrCreate("H256")),
        DictEnum.Entry(Data.SHA_3_256, preset.getOrCreate("H256"))
    )
}
