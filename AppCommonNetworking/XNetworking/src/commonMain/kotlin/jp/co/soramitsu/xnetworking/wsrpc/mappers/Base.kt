package jp.co.soramitsu.xnetworking.wsrpc.mappers

import jp.co.soramitsu.xnetworking.wsrpc.response.RpcResponse
import jp.co.soramitsu.xnetworking.wsrpc.exception.RpcException
import kotlinx.serialization.json.Json

class NullableContainer<R>(val result: R?)

interface ResponseMapper<R> {

    fun map(rpcResponse: RpcResponse, jsonMapper: Json): R
}

abstract class NullableMapper<R> : ResponseMapper<NullableContainer<R>> {

    protected abstract fun mapNullable(rpcResponse: RpcResponse, jsonMapper: Json): R?

    override fun map(rpcResponse: RpcResponse, jsonMapper: Json): NullableContainer<R> {
        val value = mapNullable(rpcResponse, jsonMapper)

        return NullableContainer(value)
    }
}

object ErrorMapper : ResponseMapper<RpcException> {

    override fun map(rpcResponse: RpcResponse, jsonMapper: Json): RpcException {
        val error = rpcResponse.error?.message

        return RpcException(error)
    }
}

class NonNullMapper<R>(
    private val nullable: ResponseMapper<NullableContainer<R>>
) : ResponseMapper<R> {

    override fun map(rpcResponse: RpcResponse, jsonMapper: Json): R {
        return nullable.map(rpcResponse, jsonMapper).result ?: throw ErrorMapper.map(
            rpcResponse,
            jsonMapper
        )
    }
}
