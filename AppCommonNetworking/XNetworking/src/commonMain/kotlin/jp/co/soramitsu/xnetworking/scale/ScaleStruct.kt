package jp.co.soramitsu.xnetworking.scale

import jp.co.soramitsu.xnetworking.scale.dataType.OptionalScaleType
import jp.co.soramitsu.xnetworking.scale.dataType.ScaleTransformer

class Field<T>(val dataType: ScaleTransformer<T>, val defaultValue: T? = null)

@Suppress("UNCHECKED_CAST", "unused")
class EncodableStruct<S : Schema<S>>(val schema: S) {
    internal val fieldsWithValues: MutableMap<Field<*>, Any?> = mutableMapOf()

    private val fields = schema.fields

    init {
        setDefaultValues()
    }

    operator fun <T> set(field: Field<T>, value: T) {
        fieldsWithValues[field] = value as Any?
    }

    operator fun <T> get(field: Field<T>): T {
        val value = fieldsWithValues[field]

        return if (value == null) {
            if (field.dataType is OptionalScaleType<*>) {
                null as T
            } else {
                throw IllegalArgumentException("Non nullable value is not set")
            }
        } else {
            value as T
        }
    }

    private fun setDefaultValues() {
        fields.filter { it.defaultValue != null }
            .forEach { fieldsWithValues[it] = it.defaultValue }
    }
}

fun <S : Schema<S>> EncodableStruct<S>.toHexString() = schema.toHexString(this)

fun <S : Schema<S>> EncodableStruct<S>.toByteArray() = schema.toByteArray(this)
