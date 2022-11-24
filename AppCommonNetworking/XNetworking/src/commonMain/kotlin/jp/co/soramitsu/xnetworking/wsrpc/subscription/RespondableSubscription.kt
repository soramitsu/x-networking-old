package jp.co.soramitsu.xnetworking.wsrpc.subscription

import jp.co.soramitsu.xnetworking.wsrpc.SocketService.ResponseListener
import jp.co.soramitsu.xnetworking.wsrpc.state.SocketStateMachine
import jp.co.soramitsu.xnetworking.wsrpc.subscription.response.SubscriptionChange

class RespondableSubscription(
    override val id: String,
    override val initiatorId: Int,
    val unsubscribeMethod: String,
    val callback: ResponseListener<SubscriptionChange>
) : SocketStateMachine.Subscription {

    override fun toString(): String {
        return "Subscription(id=$id, initiatorId=$initiatorId)"
    }
}
