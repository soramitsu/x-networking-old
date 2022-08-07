package jp.co.soramitsu.appxnetworking

import jp.co.soramitsu.xnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.SoraEnv
import jp.co.soramitsu.xnetworking.sorawallet.SoraEnvBuilder
import jp.co.soramitsu.xnetworking.txhistory.client.SubQueryClient
import kotlinx.serialization.Serializable

class NetworkService<T, R>(
    private val client: SoramitsuNetworkClient,
    private val fearlessChainsBuilder: FearlessChainsBuilder,
    private val soraEnvBuilder: SoraEnvBuilder,
    private val subQueryClient: SubQueryClient<T, R>,
) {

    suspend fun getAssets() =
        client.createJsonRequest<List<AssetRemote>>("https://raw.githubusercontent.com/soramitsu/fearless-utils/android/v2/chains/assets.json")

    suspend fun getRequest() = client.createJsonRequest<List<Int>>("https://www.github.com")

    suspend fun getChains() = fearlessChainsBuilder.getChains(
        "2.0.18",
        emptyList()
    )

    //suspend fun getApy() = subQueryClient.getSpApy()

    suspend fun getHistory(page: Long, f: (R) -> Boolean) =
        subQueryClient.getTransactionHistoryPaged(
            address = "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm",
//            address = "5ETrb47YCHE9pYxKfpm4b3bMNvKd7Zusi22yZLLHKadP5oYn",
            networkName = "sora",
            page = page,
            filter = f
        )

    suspend fun getPeers(query: String) = subQueryClient.getTransactionPeers(query, "fearless")

//    suspend fun getRewards() = subQueryClient.getReferrerRewards(
//        address = "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm",
//    )

    suspend fun getSoraEnv(): SoraEnv = soraEnvBuilder.getSoraEnv()
}

@Serializable
data class AssetRemote(
    val id: String?,
    val chainId: String?,
    val precision: Int?,
    val priceId: String? = null,
    val icon: String?
)
