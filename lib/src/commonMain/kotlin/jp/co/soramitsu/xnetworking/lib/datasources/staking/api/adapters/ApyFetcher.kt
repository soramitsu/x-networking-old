package jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters

fun interface ApyFetcher {

    suspend fun fetch(
        chainId: String,
        selectedCandidates: List<String>?
    ): Map<String, String?>

}