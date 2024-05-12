package jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models

class Unbonding(
    val amount: String,
    val timestamp: String,
    val type: DelegationAction?
) {
    enum class DelegationAction {
        STAKE,
        UNSTAKE,
        REWARD,
        DELEGATE,
        OTHER;
    }
}