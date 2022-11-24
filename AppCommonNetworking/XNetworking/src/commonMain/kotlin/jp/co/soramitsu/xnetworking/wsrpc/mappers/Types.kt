package jp.co.soramitsu.xnetworking.wsrpc.mappers

import jp.co.soramitsu.xnetworking.wsrpc.exception.RpcException
import jp.co.soramitsu.xnetworking.wsrpc.response.RpcResponse
import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.scale.EncodableStruct
import jp.co.soramitsu.xnetworking.scale.Schema
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.serializersModule
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.internal.decodeStringToJsonTree
import kotlinx.serialization.serializer
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

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
        return rpcResponse.result?.toString()
    }
}

class ScaleMapper<S : Schema<S>>(val schema: S) : NullableMapper<EncodableStruct<S>>() {
    override fun mapNullable(rpcResponse: RpcResponse, jsonMapper: Json): EncodableStruct<S>? {
        val raw = rpcResponse.result as? String ?: return null

        return schema.read(raw.fromHex())
    }
}

class ScaleCollectionMapper<S : Schema<S>>(val schema: S) :
    NullableMapper<List<EncodableStruct<S>>>() {

    override fun mapNullable(
        rpcResponse: RpcResponse,
        jsonMapper: Json
    ): List<EncodableStruct<S>>? {
        val raw = rpcResponse.result as? List<String> ?: return null

        return raw.map(schema::read)
    }
}

class POJOCollectionMapper<T: Any>(val classRef: KClass<T>) : NullableMapper<List<T>>() {

    @OptIn(InternalSerializationApi::class)
    override fun mapNullable(rpcResponse: RpcResponse, jsonMapper: Json): List<T>? {
        val raw = rpcResponse.result as? List<*> ?: return null
        return raw.map {
            val t = jsonMapper.encodeToJsonElement(it)
            jsonMapper.decodeFromJsonElement(classRef.serializer(), t) // TODO: Test .serializer() on iOS!
        }
    }
}

class POJOMapper<T: Any>(val classRef: KClass<T>) : NullableMapper<T>() {

    @OptIn(InternalSerializationApi::class)
    override fun mapNullable(rpcResponse: RpcResponse, jsonMapper: Json): T? {
        return when (rpcResponse.result) {
            is Map<*, *> -> {
                val tree = jsonMapper.encodeToJsonElement(rpcResponse.result)
                jsonMapper.decodeFromJsonElement(classRef.serializer(), tree) // TODO: Test .serializer() on iOS!
            }
            else -> rpcResponse.result as? T ?: null
        }
    }
}
