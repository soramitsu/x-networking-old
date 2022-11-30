package jp.co.soramitsu.xnetworking.wsrpc.mappers

import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import jp.co.soramitsu.xnetworking.wsrpc.exception.RpcException
import jp.co.soramitsu.xnetworking.wsrpc.response.RpcResponse
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

/**
 *  Mark that the result is always non-null and null result means that error happened
 * @throws RpcException in case of null result
 */
fun <R> NullableMapper<R>.nonNull() = NonNullMapper(this)

fun <S : Schema<S>> scale(schema: S) = ScaleMapper(schema)

fun <S : Schema<S>> scaleCollection(schema: S) = ScaleCollectionMapper(schema)

inline fun <reified T: Any> pojo() = POJOMapper(T::class)

inline fun <reified T: Any> pojoList() = POJOCollectionMapper(T::class)

fun string() = StringMapper

object StringMapper : NullableMapper<String>() {
    override fun mapNullable(rpcResponse: RpcResponse, jsonMapper: Json): String? {
        return rpcResponse.result?.jsonPrimitive?.toString()
    }
}

class ScaleMapper<S : Schema<S>>(val schema: S) : NullableMapper<EncodableStruct<S>>() {
    override fun mapNullable(rpcResponse: RpcResponse, jsonMapper: Json): EncodableStruct<S>? {
        val raw = rpcResponse.result?.jsonPrimitive?.toString() ?: return null

        return schema.read(raw.fromHex())
    }
}

class ScaleCollectionMapper<S : Schema<S>>(val schema: S) :
    NullableMapper<List<EncodableStruct<S>>>() {

    override fun mapNullable(
        rpcResponse: RpcResponse,
        jsonMapper: Json
    ): List<EncodableStruct<S>>? {
        val raw = rpcResponse.result?.jsonArray ?: return null
        val list = raw.map { it.jsonPrimitive.toString() }
        return list.map(schema::read)
    }
}

class POJOCollectionMapper<T: Any>(val classRef: KClass<T>) : NullableMapper<List<T>>() {

    @OptIn(InternalSerializationApi::class)
    override fun mapNullable(rpcResponse: RpcResponse, jsonMapper: Json): List<T>? {
        val raw = rpcResponse.result?.jsonArray ?: return null
        return raw.map {
            jsonMapper.decodeFromJsonElement(classRef.serializer(), it) // TODO: Test .serializer() on iOS!
        }
    }
}

class POJOMapper<T: Any>(val classRef: KClass<T>) : NullableMapper<T>() {

    @OptIn(InternalSerializationApi::class)
    override fun mapNullable(rpcResponse: RpcResponse, jsonMapper: Json): T? {
        val result = rpcResponse.result ?: return null
        return jsonMapper.decodeFromJsonElement(classRef.serializer(), result) // TODO: Test .serializer() on iOS!
    }
}
