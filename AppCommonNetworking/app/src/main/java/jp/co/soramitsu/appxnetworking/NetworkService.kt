package jp.co.soramitsu.appxnetworking

import jp.co.soramitsu.xnetworking.fearless.FearlessChainsBuilder
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.SoraWalletBlockExplorerInfo
import jp.co.soramitsu.xnetworking.sorawallet.envbuilder.SoraEnv
import jp.co.soramitsu.xnetworking.sorawallet.envbuilder.SoraEnvBuilder
import jp.co.soramitsu.xnetworking.sorawallet.tokenwhitelist.SoraTokenWhitelistDto
import jp.co.soramitsu.xnetworking.sorawallet.tokenwhitelist.SoraTokensWhitelistManager
import jp.co.soramitsu.xnetworking.txhistory.TxHistoryItem
import jp.co.soramitsu.xnetworking.txhistory.client.fearlesswallet.SubQueryClientForFearlessWallet
import jp.co.soramitsu.xnetworking.txhistory.client.sorawallet.SubQueryClientForSoraWallet
import kotlinx.serialization.Serializable

class NetworkService(
    private val client: SoramitsuNetworkClient,
    private val fearlessChainsBuilder: FearlessChainsBuilder,
    private val soraEnvBuilder: SoraEnvBuilder,
    private val subQueryClientForFearlessWallet: SubQueryClientForFearlessWallet,
    private val subQueryClientForSoraWallet: SubQueryClientForSoraWallet,
    private val soraWalletBlockExplorerInfo: SoraWalletBlockExplorerInfo,
    private val whitelistManager: SoraTokensWhitelistManager,
) {

    suspend fun getSoraWhitelist(): List<SoraTokenWhitelistDto> {
        return whitelistManager.getTokens()
    }

    suspend fun getAssets() =
        client.createJsonRequest<List<AssetRemote>>("https://raw.githubusercontent.com/soramitsu/fearless-utils/android/v2/chains/assets.json")

    suspend fun getRequest() = client.createJsonRequest<List<Int>>("https://www.github.com")

    suspend fun getChains() = fearlessChainsBuilder.getChains(
        "2.0.18",
        emptyList()
    )

    suspend fun getApy() = soraWalletBlockExplorerInfo.getSpApy(caseName = "1")

    suspend fun getHistorySora(page: Long, f: (TxHistoryItem) -> Boolean) =
        subQueryClientForSoraWallet.getTransactionHistoryPaged(
            address = "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm",
            networkName = "sora",
            page = page,
            filter = f
        )

    suspend fun getHistoryFearless(page: Long, f: (TxHistoryItem) -> Boolean) =
        subQueryClientForFearlessWallet.getTransactionHistoryPaged(
            address = "5ETrb47YCHE9pYxKfpm4b3bMNvKd7Zusi22yZLLHKadP5oYn",
            networkName = "fearless",
            page = page,
            filter = f
        )

    suspend fun getPeers(query: String) =
        subQueryClientForSoraWallet.getTransactionPeers(query, "sora")

    suspend fun getRewards() = soraWalletBlockExplorerInfo.getReferrerRewards(
        address = "cnUVLAjzRsrXrzEiqjxMpBwvb6YgdBy8DKibonvZgtcQY5ZKe",
        caseName = "1",
    )

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
