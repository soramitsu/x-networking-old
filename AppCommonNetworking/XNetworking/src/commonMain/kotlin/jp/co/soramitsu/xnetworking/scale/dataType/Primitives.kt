package jp.co.soramitsu.xnetworking.scale.dataType

import jp.co.soramitsu.xnetworking.common.ByteArrayOutputStream
import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.scale.ScaleReader
import jp.co.soramitsu.xnetworking.scale.ScaleWriter
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface ScaleEncoder<T> {
    fun encode(value: T): ByteArray
}

interface ScaleDecoder<T> {
    fun decode(bytes: ByteArray): T
}

abstract class ScaleTransformer<T> :
    ScaleEncoder<T>, ScaleWriter<T>,
    ScaleDecoder<T>, ScaleReader<T> {

    override fun encode(value: T): ByteArray {
        val stream = ByteArrayOutputStream()
        val writer = ScaleCodecWriter(stream)
        write(writer, value)
        return stream.toByteArray()
    }

    override fun decode(bytes: ByteArray): T {
        return read(ScaleCodecReader(bytes))
    }

    abstract fun conformsType(value: Any?): Boolean
}

val stringScale by lazy { StringScaleType() }
val booleanScale by lazy { BooleanScaleType() }
val byteArrayScale by lazy { ByteArrayScaleType() }

fun byteArraySizedScale(length: Int) = ByteArraySizedScaleType(length)

expect class StringScaleType(): ScaleTransformer<String> {

    override fun write(scaleWriter: ScaleCodecWriter, value: String)

    override fun read(reader: ScaleCodecReader): String

    override fun conformsType(value: Any?): Boolean
}

expect class BooleanScaleType(): ScaleTransformer<Boolean> {

    override fun write(scaleWriter: ScaleCodecWriter, value: Boolean)

    override fun read(reader: ScaleCodecReader): Boolean

    override fun conformsType(value: Any?): Boolean
}

expect class ByteArrayScaleType(): ScaleTransformer<ByteArray> {

    override fun write(scaleWriter: ScaleCodecWriter, value: ByteArray)

    override fun read(reader: ScaleCodecReader): ByteArray

    override fun conformsType(value: Any?): Boolean
}

expect class ByteArraySizedScaleType(
    length: Int
): ScaleTransformer<ByteArray> {

    override fun write(scaleWriter: ScaleCodecWriter, value: ByteArray)

    override fun read(reader: ScaleCodecReader): ByteArray

    override fun conformsType(value: Any?): Boolean
}
