@file:Suppress("ClassName", "UNCHECKED_CAST")

package jp.co.soramitsu.xnetworking.scale.dataType

import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import kotlin.reflect.KClass

actual class TupleScaleType<A, B> actual constructor(
    private val a: ScaleTransformer<A>,
    private val b: ScaleTransformer<B>
): ScaleTransformer<Pair<A, B>> {

    actual override fun encode(value: Pair<A, B>): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): Pair<A, B> {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class OptionalScaleType<T> actual constructor(
    private val dataType: ScaleTransformer<T>
): ScaleTransformer<T?> {

    actual override fun encode(value: T?): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): T? {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class ListScaleType<T> actual constructor(
    private val dataType: ScaleTransformer<T>
): ScaleTransformer<List<T>> {

    actual override fun encode(value: List<T>): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): List<T> {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class ScalableScaleType<S : Schema<S>> actual constructor(
    private val schema: Schema<S>
): ScaleTransformer<EncodableStruct<S>> {

    actual override fun encode(value: EncodableStruct<S>): ByteArray {
        return ByteArray(0)
    }

    actual override fun decode(bytes: ByteArray): EncodableStruct<S> {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class EnumScaleType<E : Enum<E>> actual constructor(
    private val enumClass: KClass<E>
): ScaleTransformer<E> {

    actual override fun encode(value: E): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): E {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class CollectionEnumScaleType actual constructor(
    private val values: List<String>
): ScaleTransformer<String> {

    actual override fun encode(value: String): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): String {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class UnionScaleType actual constructor(
    private val dataTypes: Array<out ScaleTransformer<*>>
): ScaleTransformer<Any?> {

    actual override fun encode(value: Any?): ByteArray {
        TODO()
    }

    actual override fun decode(bytes: ByteArray): Any? {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}