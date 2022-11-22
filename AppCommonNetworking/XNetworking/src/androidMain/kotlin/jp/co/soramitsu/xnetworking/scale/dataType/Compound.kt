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
): BaseAndroidScaleTransformer<Pair<A, B>>(), ScaleTransformer<Pair<A, B>> {

    override fun read(reader: ScaleCodecReader): Pair<A, B> {
        val a = (a as BaseAndroidScaleTransformer<A>).read(reader)
        val b = (b as BaseAndroidScaleTransformer<B>).read(reader)
        return a to b
    }

    override fun write(writer: ScaleCodecWriter, value: Pair<A, B>) {
        (a as BaseAndroidScaleTransformer<A>).write(writer, value.first)
        (b as BaseAndroidScaleTransformer<B>).write(writer, value.second)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return  value is Pair<*, *> &&
                a.conformsType(value.first) &&
                b.conformsType(value.second)
    }
}

actual class OptionalScaleType<T> actual constructor(
    private val dataType: ScaleTransformer<T>
): BaseAndroidScaleTransformer<T?>(), ScaleTransformer<T?> {

    override fun read(reader: ScaleCodecReader): T? {
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
            (dataType as BaseAndroidScaleTransformer<T>).read(reader)
        } else {
            null
        }
    }

    override fun write(writer: ScaleCodecWriter, value: T?) {
        if (dataType is BooleanScaleType) {
            writer.writeOptional(BoolWriter(), value as Boolean)
        } else {
            writer.writeOptional(dataType as BaseAndroidScaleTransformer<T?>, value)
        }
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value == null ||
                dataType.conformsType(value)
    }
}

actual class ListScaleType<T> actual constructor(
    private val dataType: ScaleTransformer<T>
): BaseAndroidScaleTransformer<List<T>>(), ScaleTransformer<List<T>> {

    override fun read(reader: ScaleCodecReader): List<T> {
        val size = compactIntScale.read(reader)
        val result = mutableListOf<T>()

        repeat(size.intValue(exactRequired = false)) {
            val element = (dataType as BaseAndroidScaleTransformer<T>).read(reader)
            result.add(element)
        }

        return result
    }

    override fun write(writer: ScaleCodecWriter, value: List<T>) {
        val size = BigInteger.fromInt(value.size)
        compactIntScale.write(writer, size)

        val androidDataType = dataType as BaseAndroidScaleTransformer<T>
        value.forEach {
            androidDataType.write(writer, it)
        }
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is List<*> && value.all(dataType::conformsType)
    }
}

actual class ScalableScaleType<S : Schema<S>> actual constructor(
    private val schema: Schema<S>
): BaseAndroidScaleTransformer<EncodableStruct<S>>(), ScaleTransformer<EncodableStruct<S>> {

    override fun read(reader: ScaleCodecReader): EncodableStruct<S> {
        return schema.read(reader)
    }

    override fun write(writer: ScaleCodecWriter, struct: EncodableStruct<S>) {
        schema.write(writer, struct)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is EncodableStruct<*> &&
                value.schema == schema
    }
}

actual class EnumScaleType<E : Enum<E>> actual constructor(
    private val enumClass: KClass<E>
): BaseAndroidScaleTransformer<E>(), ScaleTransformer<E> {

    override fun read(reader: ScaleCodecReader): E {
        val index = reader.readByte()

        return enumClass.java.enumConstants[index.toInt()]
    }

    override fun write(writer: ScaleCodecWriter, value: E) {
        writer.writeByte(value.ordinal)
    }

    actual override fun conformsType(value: Any?): Boolean {
        if (value == null) return false

        return value::class == enumClass
    }
}

actual class CollectionEnumScaleType actual constructor(
    private val values: List<String>
): BaseAndroidScaleTransformer<String>(), ScaleTransformer<String> {

    override fun read(reader: ScaleCodecReader): String {
        val index = reader.readByte()
        return values[index.toInt()]
    }

    override fun write(writer: ScaleCodecWriter, value: String) {
        val index = values.indexOf(value)

        if (index == -1) {
            throw java.lang.IllegalArgumentException("No $value in $values")
        }

        writer.writeByte(index)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return value is String
    }
}

actual class UnionScaleType actual constructor(
    private val dataTypes: Array<out ScaleTransformer<*>>
): BaseAndroidScaleTransformer<Any?>(), ScaleTransformer<Any?> {

    override fun read(reader: ScaleCodecReader): Any? {
        val typeIndex = reader.readByte()
        val type = dataTypes[typeIndex.toInt()]

        return (type as BaseAndroidScaleTransformer<Any?>).read(reader)
    }

    override fun write(writer: ScaleCodecWriter, value: Any?) {
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

        val type = dataTypes[typeIndex] as BaseAndroidScaleTransformer<Any?>

        writer.write(uInt8Scale, typeIndex.toUByte())
        writer.write(type, value)
    }

    actual override fun conformsType(value: Any?): Boolean {
        return dataTypes.any { it.conformsType(value) }
    }
}