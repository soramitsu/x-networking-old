package jp.co.soramitsu.xnetworking.scale

import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.extensions.toHexString
import jp.co.soramitsu.xnetworking.scale.dataType.OptionalScaleType
import jp.co.soramitsu.xnetworking.scale.dataType.ScaleTransformer

abstract class BaseSchema<S : Schema<S>> {

    companion object;

    internal val fields: MutableList<Field<*>> = mutableListOf()

    fun <T> field(dataType: ScaleTransformer<T>, default: T?): Field<T> {
        val field = Field(dataType, default)

        fields += field

        return field
    }

    fun <T> nullableField(dataType: OptionalScaleType<T>, default: T?): Field<T?> {
        val field = Field(dataType, default)

        fields += field

        return field
    }

    fun readOrNull(source: String): EncodableStruct<S>? {
        return try {
            read(source.fromHex())
        } catch (_: Exception) {
            return null
        }
    }

    fun read(scale: String): EncodableStruct<S> {
        return read(scale.fromHex())
    }

    abstract fun read(bytes: ByteArray): EncodableStruct<S>

    abstract fun toByteArray(struct: EncodableStruct<S>): ByteArray

    fun toHexString(struct: EncodableStruct<S>): String =
        toByteArray(struct).toHexString(withPrefix = true)
}

@Suppress("UNCHECKED_CAST")
expect abstract class Schema<S : Schema<S>>() : BaseSchema<S> {

    override fun read(bytes: ByteArray): EncodableStruct<S>

    override fun toByteArray(struct: EncodableStruct<S>): ByteArray
}