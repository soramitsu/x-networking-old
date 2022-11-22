@file:Suppress("ClassName", "UNCHECKED_CAST")

package jp.co.soramitsu.xnetworking.scale.dataType

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

    override fun encode(value: Pair<A, B>): ByteArray

    override fun decode(bytes: ByteArray): Pair<A, B>

    override fun conformsType(value: Any?): Boolean
}

expect class OptionalScaleType<T>(
    dataType: ScaleTransformer<T>
): ScaleTransformer<T?> {

    override fun encode(value: T?): ByteArray

    override fun decode(bytes: ByteArray): T?

    override fun conformsType(value: Any?): Boolean
}

expect class ListScaleType<T>(
    dataType: ScaleTransformer<T>
): ScaleTransformer<List<T>> {

    override fun encode(value: List<T>): ByteArray

    override fun decode(bytes: ByteArray): List<T>

    override fun conformsType(value: Any?): Boolean
}

expect class ScalableScaleType<S : Schema<S>>(
    schema: Schema<S>
): ScaleTransformer<EncodableStruct<S>> {

    override fun encode(value: EncodableStruct<S>): ByteArray

    override fun decode(bytes: ByteArray): EncodableStruct<S>

    override fun conformsType(value: Any?): Boolean
}

expect class EnumScaleType<E : Enum<E>>(
    enumClass: KClass<E>
): ScaleTransformer<E> {

    override fun encode(value: E): ByteArray

    override fun decode(bytes: ByteArray): E

    override fun conformsType(value: Any?): Boolean
}

expect class CollectionEnumScaleType(
    values: List<String>
): ScaleTransformer<String> {

    override fun encode(value: String): ByteArray

    override fun decode(bytes: ByteArray): String

    override fun conformsType(value: Any?): Boolean
}

expect class UnionScaleType(
    dataTypes: Array<out ScaleTransformer<*>>
): ScaleTransformer<Any?> {

    override fun encode(value: Any?): ByteArray

    override fun decode(bytes: ByteArray): Any?

    override fun conformsType(value: Any?): Boolean
}