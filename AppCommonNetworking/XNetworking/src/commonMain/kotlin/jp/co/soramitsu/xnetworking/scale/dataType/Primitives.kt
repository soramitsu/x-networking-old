package jp.co.soramitsu.xnetworking.scale.dataType

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface ScaleEncoder<T> {
    fun encode(value: T): ByteArray
}

interface ScaleDecoder<T> {
    fun decode(bytes: ByteArray): T
}

interface ScaleTransformer<T> : ScaleEncoder<T>, ScaleDecoder<T> {

    override fun encode(value: T): ByteArray

    override fun decode(bytes: ByteArray): T

    fun conformsType(value: Any?): Boolean
}

val stringScale by lazy { StringScaleType() }
val booleanScale by lazy { BooleanScaleType() }
val byteArrayScale by lazy { ByteArrayScaleType() }

fun byteArraySizedScale(length: Int) = ByteArraySizedScaleType(length)

expect class StringScaleType(): ScaleTransformer<String> {

    override fun encode(value: String): ByteArray

    override fun decode(bytes: ByteArray): String
    override fun conformsType(value: Any?): Boolean
}

@OptIn(ExperimentalContracts::class)
internal inline fun <reified T> checkType(value: Any?): Boolean {
    contract {
        returns(true) implies (value is T)
    }
    return value is T
}

expect class BooleanScaleType(): ScaleTransformer<Boolean> {

    override fun encode(value: Boolean): ByteArray

    override fun decode(bytes: ByteArray): Boolean

    override fun conformsType(value: Any?): Boolean
}

expect class ByteArrayScaleType(): ScaleTransformer<ByteArray> {

    override fun encode(value: ByteArray): ByteArray

    override fun decode(bytes: ByteArray): ByteArray

    override fun conformsType(value: Any?): Boolean
}

expect class ByteArraySizedScaleType(
    length: Int
): ScaleTransformer<ByteArray> {

    override fun encode(value: ByteArray): ByteArray

    override fun decode(bytes: ByteArray): ByteArray

    override fun conformsType(value: Any?): Boolean
}
