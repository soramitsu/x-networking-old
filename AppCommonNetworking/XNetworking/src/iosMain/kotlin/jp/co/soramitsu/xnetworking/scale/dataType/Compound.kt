@file:Suppress("ClassName")

package jp.co.soramitsu.xnetworking.scale.dataType

import jp.co.soramitsu.xnetworking.scale.ScaleCodecReader
import jp.co.soramitsu.xnetworking.scale.ScaleCodecWriter
import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import kotlin.reflect.KClass

actual class TupleScaleType<A, B> actual constructor(
    private val a: ScaleTransformer<A>,
    private val b: ScaleTransformer<B>
): ScaleTransformer<Pair<A, B>>() {

    actual override fun read(reader: ScaleCodecReader): Pair<A, B> {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Pair<A, B>) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class OptionalScaleType<T> actual constructor(
    private val dataType: ScaleTransformer<T>
): ScaleTransformer<T?>() {

    actual override fun read(reader: ScaleCodecReader): T? {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: T?) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class ListScaleType<T> actual constructor(
    private val dataType: ScaleTransformer<T>
): ScaleTransformer<List<T>>() {

    actual override fun read(reader: ScaleCodecReader): List<T> {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: List<T>) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class ScalableScaleType<S : Schema<S>> actual constructor(
    private val schema: Schema<S>
): ScaleTransformer<EncodableStruct<S>>() {

    actual override fun read(reader: ScaleCodecReader): EncodableStruct<S> {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: EncodableStruct<S>) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class EnumScaleType<E : Enum<E>> actual constructor(
    private val enumClass: KClass<E>
): ScaleTransformer<E>() {

    actual override fun read(reader: ScaleCodecReader): E {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: E) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}

actual class CollectionEnumScaleType actual constructor(
    private val values: List<String>
): ScaleTransformer<String>() {

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

actual class UnionScaleType actual constructor(
    private val dataTypes: Array<out ScaleTransformer<*>>
): ScaleTransformer<Any?>() {

    actual override fun read(reader: ScaleCodecReader): Any? {
        TODO()
    }

    actual override fun write(scaleWriter: ScaleCodecWriter, value: Any?) {
        TODO()
    }

    actual override fun conformsType(value: Any?): Boolean {
        TODO()
    }
}