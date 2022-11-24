@file:Suppress("EXPERIMENTAL_API_USAGE")

package jp.co.soramitsu.xnetworking.wsrpc

import jp.co.soramitsu.xnetworking.wsrpc.mappers.ResponseMapper
import jp.co.soramitsu.xnetworking.wsrpc.request.DeliveryType
import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.RuntimeRequest
import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.UnsubscribeMethodResolver
import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.storage.MultiplexerCallback
import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.storage.StorageSubscriptionMultiplexer.Builder
import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.storage.StorageSubscriptionMultiplexer.Change
import jp.co.soramitsu.xnetworking.wsrpc.response.RpcResponse
import jp.co.soramitsu.xnetworking.wsrpc.state.SocketStateMachine.State
import jp.co.soramitsu.xnetworking.wsrpc.subscription.response.SubscriptionChange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <R> SocketService.executeAsync(
    request: RuntimeRequest,
    deliveryType: DeliveryType = DeliveryType.AT_LEAST_ONCE,
    mapper: ResponseMapper<R>
): R {
    val response = executeAsync(request, deliveryType)

    return withContext(Dispatchers.Default) {
        mapper.map(response, jsonMapper)
    }
}

suspend fun SocketService.executeAsync(
    request: RuntimeRequest,
    deliveryType: DeliveryType = DeliveryType.AT_LEAST_ONCE
) = suspendCancellableCoroutine<RpcResponse> { cont ->
    val cancellable =
        executeRequest(
            request, deliveryType,
            object : SocketService.ResponseListener<RpcResponse> {
                override fun onNext(response: RpcResponse) {
                    cont.resume(response)
                }

                override fun onError(throwable: Throwable) {
                    cont.resumeWithException(throwable)
                }
            }
        )

    cont.invokeOnCancellation {
        cancellable.cancel()
    }
}

fun SocketService.subscriptionFlow(
    request: RuntimeRequest,
    unsubscribeMethod: String = UnsubscribeMethodResolver.resolve(request.method)
): Flow<SubscriptionChange> =
    callbackFlow {
        val cancellable =
            subscribe(
                request,
                object : SocketService.ResponseListener<SubscriptionChange> {
                    override fun onNext(response: SubscriptionChange) {
                        trySend(response)
                    }

                    override fun onError(throwable: Throwable) {
                        close(throwable)
                    }
                },
                unsubscribeMethod
            )

        awaitClose {
            cancellable.cancel()
        }
    }

fun Builder.subscribe(key: String): Flow<Change> {
    val callback = FlowCallback()

    subscribe(key, callback)

    return callback.collector
        .map { it.getOrThrow() }
}

private class FlowCallback : MultiplexerCallback {

    val collector = MutableSharedFlow<Result<Change>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun onNext(response: Change) {
        collector.tryEmit(Result.success(response))
    }

    override fun onError(throwable: Throwable) {
        collector.tryEmit(Result.failure(throwable))
    }
}
