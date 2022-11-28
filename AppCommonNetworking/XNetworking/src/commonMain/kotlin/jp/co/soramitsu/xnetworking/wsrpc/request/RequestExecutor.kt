package jp.co.soramitsu.xnetworking.wsrpc.request

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

class RequestExecutor {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val futures = mutableListOf<Deferred<*>>()

    fun execute(action: suspend () -> Unit) {
        var future: Deferred<*>? = null

        future = coroutineScope.async {
            action.invoke()
            futures.remove(future)
        }
        futures += future
    }

    fun reset() {
        futures.iterator().forEach { it.cancel() }

        futures.clear()
    }
}