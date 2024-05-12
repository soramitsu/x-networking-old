package jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters

fun interface ValidatorsFetcher {

    suspend fun fetch(
        chainId: String,
        stashAccountAddress: String,
        historicalRange: List<String>
    ): List<String>

}