package jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters

import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.models.Unbonding

fun interface UnbondingFetcher {

    suspend fun fetch(
        chainId: String,
        delegatorAddress: String,
        collatorAddress: String
    ): List<Unbonding>

}