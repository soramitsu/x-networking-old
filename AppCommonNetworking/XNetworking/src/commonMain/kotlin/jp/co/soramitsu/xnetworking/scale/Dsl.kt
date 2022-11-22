@file:Suppress("unused", "EXPERIMENTAL_API_USAGE")

package jp.co.soramitsu.xnetworking.scale

import com.ionspin.kotlin.bignum.integer.BigInteger
import jp.co.soramitsu.xnetworking.scale.dataType.EnumScaleType
import jp.co.soramitsu.xnetworking.scale.dataType.ScaleTransformer
import jp.co.soramitsu.xnetworking.scale.dataType.booleanScale
import jp.co.soramitsu.xnetworking.scale.dataType.byteArrayScale
import jp.co.soramitsu.xnetworking.scale.dataType.byteArraySizedScale
import jp.co.soramitsu.xnetworking.scale.dataType.byteScale
import jp.co.soramitsu.xnetworking.scale.dataType.compactIntScale
import jp.co.soramitsu.xnetworking.scale.dataType.listScale
import jp.co.soramitsu.xnetworking.scale.dataType.longScale
import jp.co.soramitsu.xnetworking.scale.dataType.scalableScale
import jp.co.soramitsu.xnetworking.scale.dataType.stringScale
import jp.co.soramitsu.xnetworking.scale.dataType.tupleScale
import jp.co.soramitsu.xnetworking.scale.dataType.uInt128Scale
import jp.co.soramitsu.xnetworking.scale.dataType.uInt16Scale
import jp.co.soramitsu.xnetworking.scale.dataType.uInt32Scale
import jp.co.soramitsu.xnetworking.scale.dataType.uInt64Scale
import jp.co.soramitsu.xnetworking.scale.dataType.uInt8Scale
import jp.co.soramitsu.xnetworking.scale.dataType.unionScale
import kotlin.reflect.KClass

typealias StructBuilder<SCHEMA> = (EncodableStruct<SCHEMA>) -> Unit

operator fun <S : Schema<S>> S.invoke(block: StructBuilder<S>? = null): EncodableStruct<S> {
    val struct = EncodableStruct(this)

    block?.invoke(struct)

    return struct
}

fun <S : Schema<S>> S.string(default: String? = null) = NonNullFieldDelegate(stringScale, this, default)

fun <S : Schema<S>> S.uint8(default: UByte? = null) = NonNullFieldDelegate(uInt8Scale, this, default)

fun <S : Schema<S>> S.uint32(default: UInt? = null) = NonNullFieldDelegate(uInt32Scale, this, default)

fun <S : Schema<S>> S.uint16(default: Int? = null) = NonNullFieldDelegate(uInt16Scale, this, default)

fun <S : Schema<S>> S.uint128(default: BigInteger? = null) = NonNullFieldDelegate(uInt128Scale, this, default)

fun <S : Schema<S>> S.bool(default: Boolean? = null) = NonNullFieldDelegate(booleanScale, this, default)

fun <S : Schema<S>> S.uint64(default: BigInteger? = null) = NonNullFieldDelegate(uInt64Scale, this, default)

fun <S : Schema<S>, T : Schema<T>> S.schema(schema: T, default: EncodableStruct<T>? = null) =
    NonNullFieldDelegate(scalableScale(schema), this, default)

fun <S : Schema<S>, T, D : ScaleTransformer<T>> S.vector(
    type: D,
    default: List<T>? = null
) = NonNullFieldDelegate(listScale(type), this, default)

fun <S : Schema<S>, T : Schema<T>> S.vector(
    schema: T,
    default: List<EncodableStruct<T>>? = null
) = NonNullFieldDelegate(listScale(scalableScale(schema)), this, default)

fun <S : Schema<S>> S.byte(default: Byte? = null) = NonNullFieldDelegate(byteScale, this, default)

fun <S : Schema<S>> S.compactInt(default: BigInteger? = null) = NonNullFieldDelegate(compactIntScale, this, default)

fun <S : Schema<S>> S.sizedByteArray(length: Int, default: ByteArray? = null): NonNullFieldDelegate<S, ByteArray> {
    if (default != null) {
        require(length == default.size)
    }

    return NonNullFieldDelegate(byteArraySizedScale(length), this, default)
}

fun <S : Schema<S>, A, B> S.pair(
    first: ScaleTransformer<A>,
    second: ScaleTransformer<B>,
    default: Pair<A, B>? = null
) = NonNullFieldDelegate(tupleScale(first, second), this, default)

fun <S : Schema<S>> S.byteArray(default: ByteArray? = null): NonNullFieldDelegate<S, ByteArray> {
    return NonNullFieldDelegate(byteArrayScale, this, default)
}

fun <S : Schema<S>> S.long(default: Long? = null) = NonNullFieldDelegate(longScale, this, default)

fun <S : Schema<S>> S.enum(vararg types: ScaleTransformer<*>, default: Any? = null) = NonNullFieldDelegate(
    unionScale(types), this, default)

fun <S : Schema<S>, E : Enum<E>> S.enum(enumClass: KClass<E>, default: E? = null) = NonNullFieldDelegate(
    EnumScaleType(enumClass), this, default)

fun <S : Schema<S>, T> S.custom(type: ScaleTransformer<T>, default: T? = null) = NonNullFieldDelegate(type, this, default)
