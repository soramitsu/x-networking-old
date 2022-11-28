package jp.co.soramitsu.xnetworking.wsrpc.subscription.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class SubscriptionChange(
    @SerialName("jsonrpc")
    val jsonrpc: String,
    @SerialName("method")
    val method: String,
    @SerialName("params")
    val params: Params
) {

    @Serializable
    class Params(
        @SerialName("result")
        val result: JsonObject,
        @SerialName("subscription")
        val subscription: String
    )

    val subscriptionId: String
        get() = params.subscription

    override fun toString() = "SubscriptionChange(${params.subscription})"
}

internal fun notValidResult(result: Any?, ofWhat: String): Nothing {
    throw IllegalArgumentException("$result is not a valid $ofWhat result")
}
