package jp.co.soramitsu.xnetworking.scale.dataType

import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter

actual class StringScaleType: ScaleTransformer<String>() {

    actual override fun read(reader: ScaleCodecReader): String {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: String) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class BooleanScaleType: ScaleTransformer<Boolean>() {

    actual override fun read(reader: ScaleCodecReader): Boolean {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Boolean) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class ByteArrayScaleType: ScaleTransformer<ByteArray>() {

    actual override fun read(reader: ScaleCodecReader): ByteArray {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: ByteArray) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class ByteArraySizedScaleType actual constructor(
    private val length: Int
): ScaleTransformer<ByteArray>() {

    actual override fun read(reader: ScaleCodecReader): ByteArray {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: ByteArray) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}