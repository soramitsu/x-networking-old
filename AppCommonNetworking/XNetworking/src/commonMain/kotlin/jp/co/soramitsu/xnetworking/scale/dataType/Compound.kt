@file:Suppress("ClassName")

package jp.co.soramitsu.xnetworking.scale.dataType

import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import kotlin.reflect.KClass

fun <A, B> tupleScale(
    a: ScaleTransformer<A>,
    b: ScaleTransformer<B>
) = TupleScaleType(a, b)

fun <T> optionalScale(
    dataType: ScaleTransformer<T>,
) = OptionalScaleType(dataType)

fun <T> listScale(
    dataType: ScaleTransformer<T>,
) = ListScaleType(dataType)

fun <S: Schema<S>> scalableScale(
    schema: Schema<S>
) = ScalableScaleType(schema)

fun <E: Enum<E>> enumScale(
    enumClass: KClass<E>
) = EnumScaleType(enumClass)

fun collectionEnumScale(
    values: List<String>
) = CollectionEnumScaleType(values)

fun unionScale(
    dataTypes: Array<out ScaleTransformer<*>>
) = UnionScaleType(dataTypes)

expect class TupleScaleType<A, B>(
    a: ScaleTransformer<A>,
    b: ScaleTransformer<B>
): ScaleTransformer<Pair<A, B>> {

    override fun write(scaleWriter: ScaleCodecWriter, value: Pair<A, B>)

    override fun read(reader: ScaleCodecReader): Pair<A, B>

    override fun conformsType(value: Any?): Boolean
}

expect class OptionalScaleType<T>(
    dataType: ScaleTransformer<T>
): ScaleTransformer<T?> {

    override fun write(scaleWriter: ScaleCodecWriter, value: T?)

    override fun read(reader: ScaleCodecReader): T?

    override fun conformsType(value: Any?): Boolean
}

expect class ListScaleType<T>(
    dataType: ScaleTransformer<T>
): ScaleTransformer<List<T>> {

    override fun write(scaleWriter: ScaleCodecWriter, value: List<T>)

    override fun read(reader: ScaleCodecReader): List<T>

    override fun conformsType(value: Any?): Boolean
}

expect class ScalableScaleType<S : Schema<S>>(
    schema: Schema<S>
): ScaleTransformer<EncodableStruct<S>> {

    override fun write(scaleWriter: ScaleCodecWriter, value: EncodableStruct<S>)

    override fun read(reader: ScaleCodecReader): EncodableStruct<S>

    override fun conformsType(value: Any?): Boolean
}

expect class EnumScaleType<E : Enum<E>>(
    enumClass: KClass<E>
): ScaleTransformer<E> {

    override fun write(scaleWriter: ScaleCodecWriter, value: E)

    override fun read(reader: ScaleCodecReader): E

    override fun conformsType(value: Any?): Boolean
}

expect class CollectionEnumScaleType(
    values: List<String>
): ScaleTransformer<String> {

    override fun write(scaleWriter: ScaleCodecWriter, value: String)

    override fun read(reader: ScaleCodecReader): String

    override fun conformsType(value: Any?): Boolean
}

expect class UnionScaleType(
    dataTypes: Array<out ScaleTransformer<*>>
): ScaleTransformer<Any?> {

    override fun write(scaleWriter: ScaleCodecWriter, value: Any?)

    override fun read(reader: ScaleCodecReader): Any?

    override fun conformsType(value: Any?): Boolean
}