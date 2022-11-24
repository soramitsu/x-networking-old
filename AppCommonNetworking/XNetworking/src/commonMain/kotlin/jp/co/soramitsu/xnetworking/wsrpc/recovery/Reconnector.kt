package jp.co.soramitsu.xnetworking.wsrpc.recovery

private val DEFAULT_RECONNECT_STRATEGY =
    ExponentialReconnectStrategy(initialTime = 300L, base = 2.0)

/*class Reconnector(
    private val strategy: ReconnectStrategy = DEFAULT_RECONNECT_STRATEGY,
    private val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
) {
    private var inProgress: Future<*>? = null

    fun scheduleConnect(currentAttempt: Int, runnable: () -> Unit) {
        reset()

        inProgress = executor.schedule(
            wrapReconnectAction(runnable),
            strategy.getTimeForReconnect(currentAttempt),
            TimeUnit.MILLISECONDS
        )
    }

    fun reset() {
        inProgress?.cancel(true)
        inProgress = null
    }

    private fun wrapReconnectAction(runnable: () -> Unit) = Runnable {
        inProgress = null
        runnable.invoke()
    }
}*/

// TODO: Replace to non-mock RequestExecutor
class Reconnector(
    private val strategy: ReconnectStrategy = DEFAULT_RECONNECT_STRATEGY
) {

    fun scheduleConnect(currentAttempt: Int, runnable: () -> Unit) {
    }

    fun reset() {
    }
}