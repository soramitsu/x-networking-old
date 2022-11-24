@file:Suppress("ClassName", "UNCHECKED_CAST")

package jp.co.soramitsu.xnetworking.scale.dataType

import com.ionspin.kotlin.bignum.integer.BigInteger
import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.writer.BoolWriter
import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import kotlin.reflect.KClass

actual class TupleScaleType<A, B> actual constructor(
    private val a: ScaleTransformer<A>,
    private val b: ScaleTransformer<B>
): ScaleTransformer<Pair<A, B>>() {

    actual override fun read(reader: ScaleCodecReader): Pair<A, B> {
        val a = a.read(reader)
        val b = b.read(reader)
        return a to b
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Pair<A, B>) {
        a.write(scaleWriter, value.first)
        b.write(scaleWriter, value.second)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return  value is Pair<*, *> &&
                a.conformsType(value.first) &&
                b.conformsType(value.second)
    }
}

actual class OptionalScaleType<T> actual constructor(
    private val dataType: ScaleTransformer<T>
): ScaleTransformer<T?>() {

    actual override fun read(reader: ScaleCodecReader): T? {
        if (dataType is BooleanScaleType) {
            return when (reader.readByte().toInt()) {
                0 -> null
                1 -> false as T?
                2 -> true as T?
                else -> throw IllegalArgumentException("Not a optional boolean")
            }
        }

        val some: Boolean = reader.readBoolean()

        return if (some) {
            dataType.read(reader)
        } else {
            null
        }
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: T?) {
        if (dataType is BooleanScaleType) {
            scaleWriter.writeOptional(BoolWriter(), value as Boolean)
        } else {
            scaleWriter.writeOptional(dataType, value)
        }
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value == null ||
                dataType.conformsType(value)
    }
}

actual class ListScaleType<T> actual constructor(
    private val dataType: ScaleTransformer<T>
): ScaleTransformer<List<T>>() {

    actual override fun read(reader: ScaleCodecReader): List<T> {
        val size = compactIntScale.read(reader)
        val result = mutableListOf<T>()

        repeat(size.intValue(exactRequired = false)) {
            val element = dataType.read(reader)
            result.add(element)
        }

        return result
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: List<T>) {
        val size = BigInteger.fromInt(value.size)
        compactIntScale.write(scaleWriter, size)

        val androidDataType = dataType
        value.forEach {
            androidDataType.write(scaleWriter, it)
        }
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is List<*> && value.all(dataType::conformsType)
    }
}

actual class ScalableScaleType<S : Schema<S>> actual constructor(
    private val schema: Schema<S>
): ScaleTransformer<EncodableStruct<S>>() {

    actual override fun read(reader: ScaleCodecReader): EncodableStruct<S> {
        return schema.read(reader)
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: EncodableStruct<S>) {
        schema.write(scaleWriter, value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is EncodableStruct<*> &&
                value.schema == schema
    }
}

actual class EnumScaleType<E : Enum<E>> actual constructor(
    private val enumClass: KClass<E>
): ScaleTransformer<E>() {

    actual override fun read(reader: ScaleCodecReader): E {
        val index = reader.readByte()

        return enumClass.java.enumConstants[index.toInt()]
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: E) {
        scaleWriter.writeByte(value.ordinal)
    }

    actual override fun conformsType(value: Any?): Boolean {
        if (value == null) return false

        return value::class == enumClass
    }
}

actual class CollectionEnumScaleType actual constructor(
    private val values: List<String>
): ScaleTransformer<String>() {

    actual override fun read(reader: ScaleCodecReader): String {
        val index = reader.readByte()
        return values[index.toInt()]
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: String) {
        val index = values.indexOf(value)

        if (index == -1) {
            throw java.lang.IllegalArgumentException("No $value in $values")
        }

        scaleWriter.writeByte(index)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is String
    }
}

actual class UnionScaleType actual constructor(
    private val dataTypes: Array<out ScaleTransformer<*>>
): ScaleTransformer<Any?>() {

    actual override fun read(reader: ScaleCodecReader): Any? {
        val typeIndex = reader.readByte()
        val type = dataTypes[typeIndex.toInt()]
        return type.read(reader)
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Any?) {
        val typeIndex = dataTypes.indexOfFirst { it.conformsType(value) }

        if (typeIndex == -1) {
            throw java.lang.IllegalArgumentException(
                "Unknown type ${value?.javaClass} for this enum. Supported: ${
                    dataTypes.joinToString(
                        ", "
                    )
                }"
            )
        }

        val type = dataTypes[typeIndex] as? ScaleTransformer<Any?>

        scaleWriter.write(uInt8Scale, typeIndex.toUByte())
        scaleWriter.write(type, value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return dataTypes.any { it.conformsType(value) }
    }
}