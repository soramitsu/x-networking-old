package jp.co.soramitsu.xnetworking.wsrpc.request

typealias SendAction = () -> Unit

/*
class RequestExecutor(private val executor: ExecutorService = Executors.newSingleThreadExecutor()) {
    private val futures = mutableListOf<Future<*>>()

    fun execute(action: SendAction) {
        var future: Future<*>? = null

        future = executor.submit {
            action()

            futures.remove(future)
        }

        futures += future
    }

    fun reset() {
        futures.iterator().forEach { it.cancel(true) }

        futures.clear()
    }
}
*/

// TODO: Replace to non-mock RequestExecutor
class RequestExecutor {

    fun execute(action: SendAction) {

    }

    fun reset() {

    }
}