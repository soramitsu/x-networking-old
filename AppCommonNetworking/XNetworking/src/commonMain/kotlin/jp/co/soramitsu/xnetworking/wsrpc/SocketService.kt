package jp.co.soramitsu.xnetworking.wsrpc

import jp.co.soramitsu.xnetworking.wsrpc.exception.ConnectionClosedException
import jp.co.soramitsu.xnetworking.wsrpc.logging.Logger
import jp.co.soramitsu.xnetworking.wsrpc.mappers.nonNull
import jp.co.soramitsu.xnetworking.wsrpc.mappers.string
import jp.co.soramitsu.xnetworking.wsrpc.recovery.Reconnector
import jp.co.soramitsu.xnetworking.wsrpc.request.DeliveryType
import jp.co.soramitsu.xnetworking.wsrpc.request.RequestExecutor
import jp.co.soramitsu.xnetworking.wsrpc.request.RespondableSendable
import jp.co.soramitsu.xnetworking.wsrpc.request.runtime.RuntimeRequest
import jp.co.soramitsu.xnetworking.wsrpc.response.RpcResponse
import jp.co.soramitsu.xnetworking.wsrpc.socket.RpcSocket
import jp.co.soramitsu.xnetworking.wsrpc.socket.RpcSocketListener
import jp.co.soramitsu.xnetworking.wsrpc.state.SocketStateMachine
import jp.co.soramitsu.xnetworking.wsrpc.state.SocketStateMachine.Event
import jp.co.soramitsu.xnetworking.wsrpc.state.SocketStateMachine.SideEffect
import jp.co.soramitsu.xnetworking.wsrpc.state.SocketStateMachine.State
import jp.co.soramitsu.xnetworking.wsrpc.subscription.RespondableSubscription
import jp.co.soramitsu.xnetworking.wsrpc.subscription.response.SubscriptionChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json
import kotlin.jvm.Synchronized

class SocketService(
    val jsonMapper: Json,
    private val logger: Logger,
    private val reconnector: Reconnector
) : RpcSocketListener {

    private val requestExecutor = RequestExecutor()

    private val socketConnectionCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var socket: RpcSocket? = null

    private val stateFlow = MutableStateFlow<State>(State.Disconnected)
    private val mutex = Mutex()

    fun started() = stateFlow.value !is State.Disconnected

    /**
     * Initiate new connection to the given url.
     * Only has effect when called from [SocketStateMachine.State.Disconnected] state.
     * Meaning, this is the first method that should be called after create [SocketService] instance
     *
     * @param url - url to connect to
     * @param remainPaused - true if socket should start in [SocketStateMachine.State.Paused] state,
     * delaying connection until [resume] is called
     */
    fun start(url: String, remainPaused: Boolean = false) {
        updateState(Event.Start(url, remainPaused))
    }

    /**
     * Closes connection and forgets about all requests and subscriptions.
     * Should be used when socket connection is no longer needed.
     */
    fun stop() {
        updateState(Event.Stop)
    }

    /**
     * Seamlessly switches connection url saving all pending & active requests or subscriptions
     * Should be used instead of
     * ```
     *     stop()
     *     start(newURl)
     * ```
     */
    fun switchUrl(url: String) {
        updateState(Event.SwitchUrl(url))
    }

    /**
     * Closes connection but saves all subscriptions and [DeliveryType.AT_LEAST_ONCE] requests.
     * Reports [ConnectionClosedException] to [DeliveryType.AT_MOST_ONCE] requests.
     */
    fun pause() {
        updateState(Event.Pause)
    }

    /**
     * Resumes connection from [SocketStateMachine.State.Paused] state,
     * recovering all subscriptions and [DeliveryType.AT_LEAST_ONCE] requests
     */
    fun resume() {
        updateState(Event.Resume)
    }

    fun subscribeToState(): Flow<State> {
        return stateFlow.asStateFlow()
    }

    suspend fun subscribe(
        request: RuntimeRequest,
        callback: ResponseListener<SubscriptionChange>,
        unsubscribeMethod: String
    ): Cancellable {
        return executeRequest(
            request,
            DeliveryType.ON_RECONNECT,
            SubscribedCallback(request.id, unsubscribeMethod, callback)
        )
    }

    suspend fun executeRequest(
        runtimeRequest: RuntimeRequest,
        deliveryType: DeliveryType = DeliveryType.AT_LEAST_ONCE,
        callback: ResponseListener<RpcResponse>
    ): Cancellable {
        val sendable = RespondableSendable(runtimeRequest, deliveryType, callback)

        updateState(Event.Send(sendable))

        return RequestCancellable(sendable)
    }

    override suspend fun onResponse(rpcResponse: RpcResponse) {
        updateState(Event.SendableResponse(rpcResponse))
    }

    override suspend fun onResponse(subscriptionChange: SubscriptionChange) {
        updateState(Event.SubscriptionResponse(subscriptionChange))
    }

    @Synchronized
    override fun onConnected() {
        updateState(Event.Connected)
    }

    override fun onSocketClosed() {
        updateState(Event.ConnectionError(ConnectionClosedException()))
    }

    private fun updateState(event: Event) {
        socketConnectionCoroutineScope.launch {
            mutex.withLock {
                val state = stateFlow.value
                val (newState, sideEffects) = SocketStateMachine.transition(state, event)
                stateFlow.update { newState }

                logger.log("[STATE MACHINE][TRANSITION] $event : $state -> $newState")

                sideEffects.forEach(::consumeSideEffect)
            }
        }
    }

    private fun consumeSideEffect(sideEffect: SideEffect) {
        logger.log("[STATE MACHINE][SIDE EFFECT] $sideEffect")

        when (sideEffect) {
            is SideEffect.ResponseToSendable -> respondToRequest(
                sideEffect.sendable,
                sideEffect.response
            )
            is SideEffect.RespondSendablesError -> respondError(
                sideEffect.sendables,
                sideEffect.error
            )
            is SideEffect.RespondToSubscription -> respondToSubscription(
                sideEffect.subscription,
                sideEffect.change
            )
            is SideEffect.SendSendables -> sendToSocket(sideEffect.sendables)
            is SideEffect.Connect -> connectToSocket(sideEffect.url)
            is SideEffect.ScheduleReconnect -> scheduleReconnect(sideEffect.attempt)
            is SideEffect.Disconnect -> disconnect()
            is SideEffect.Unsubscribe -> unsubscribe(sideEffect.subscription)
        }
    }

    private fun respondToRequest(
        sendable: SocketStateMachine.Sendable,
        response: RpcResponse
    ) {
        require(sendable is RespondableSendable)

        sendable.callback.onNext(response)
    }

    private fun respondError(sendables: Set<SocketStateMachine.Sendable>, throwable: Throwable) {
        sendables.forEach {
            require(it is RespondableSendable)

            it.callback.onError(throwable)
        }
    }

    private fun respondToSubscription(
        subscription: SocketStateMachine.Subscription,
        response: SubscriptionChange
    ) {
        require(subscription is RespondableSubscription)

        subscription.callback.onNext(response)
    }

    private fun sendToSocket(sendables: Set<SocketStateMachine.Sendable>) {
        requestExecutor.execute {
            sendables.forEach {
                require(it is RespondableSendable)

                socket!!.sendRpcRequest(it.request)
            }
        }
    }

    private fun scheduleReconnect(attempt: Int) =
        reconnector.scheduleConnect(attempt, ::readyForReconnect)

    private fun readyForReconnect() {
        updateState(Event.ReadyToReconnect)
    }

    private fun connectToSocket(url: String) {
        socketConnectionCoroutineScope.launch {
            mutex.withLock {
                reconnector.reset()
                socket = createSocket(url)
            }
            socket!!.connectAsync()
        }
    }

    private fun createSocket(url: String) =
        RpcSocket(url, this, logger, jsonMapper)

    private fun disconnect() {
        val closeableSocket = socket ?: return

        socketConnectionCoroutineScope.launch {
            closeableSocket.disconnect()
            if (socket == closeableSocket) {
                socket = null
            }
        }

        requestExecutor.reset()
        reconnector.reset()
    }

    private fun unsubscribe(subscription: SocketStateMachine.Subscription) {
        require(subscription is RespondableSubscription)

        val unsubscribeRequest =
            RuntimeRequest(subscription.unsubscribeMethod, listOf(subscription.id))

        socketConnectionCoroutineScope.launch {
            executeRequest(
                unsubscribeRequest,
                deliveryType = DeliveryType.AT_MOST_ONCE,
                FireAndForgetCallback()
            )
        }
    }

    interface ResponseListener<R> {
        fun onNext(response: R)

        fun onError(throwable: Throwable)
    }

    interface Cancellable {
        fun cancel()
    }

    inner class SubscribedCallback(
        private val initiatorId: Int,
        private val unsubscribeMethod: String,
        private val subscriptionCallback: ResponseListener<SubscriptionChange>
    ) : ResponseListener<RpcResponse> {

        override fun onNext(response: RpcResponse) {
            val id = try {
                string().nonNull().map(response, jsonMapper)
            } catch (e: Exception) {
                subscriptionCallback.onError(e)

                return
            }

            val subscription =
                RespondableSubscription(id, initiatorId, unsubscribeMethod, subscriptionCallback)

            updateState(Event.Subscribed(subscription))
        }

        override fun onError(throwable: Throwable) {
            subscriptionCallback.onError(throwable)
        }
    }

    inner class RequestCancellable(
        private val sendable: SocketStateMachine.Sendable
    ) : Cancellable {

        override fun cancel() {
            updateState(Event.Cancel(sendable))
        }
    }

    private class FireAndForgetCallback : ResponseListener<RpcResponse> {

        override fun onNext(response: RpcResponse) {
            // do nothing
        }

        override fun onError(throwable: Throwable) {
            // do nothing
        }
    }
}
