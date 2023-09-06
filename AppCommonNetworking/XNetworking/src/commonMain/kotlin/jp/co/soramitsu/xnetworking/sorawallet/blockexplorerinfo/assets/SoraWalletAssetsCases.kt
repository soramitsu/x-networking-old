package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.assets

import io.ktor.http.HttpMethod
import jp.co.soramitsu.xnetworking.common.Utils.toDoubleNan
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.BasicCases
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.assets.case0.SoraWalletAssetsCase0Response
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.assets.case0.graphQLRequestSoraWalletAssetsCase0
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.SubQueryRequest

internal interface SoraWalletAssetsCase {

    suspend fun getAssetsInfo(
        url: String,
        networkClient: SoramitsuNetworkClient,
        tokenIds: List<String>,
        timestamp: Long,
    ): List<AssetsInfo>
}

internal object SoraWalletAssetsCases : BasicCases<SoraWalletAssetsCase>() {
    override fun provideInstance(caseName: String): SoraWalletAssetsCase {
        return when (caseName) {
            "0" -> SoraWalletAssetsCase0()
            else -> throw IllegalArgumentException("SoraWalletAssetsCases [$caseName] not found")
        }
    }
}

private class SoraWalletAssetsCase0 : SoraWalletAssetsCase {
    override suspend fun getAssetsInfo(
        url: String,
        networkClient: SoramitsuNetworkClient,
        tokenIds: List<String>,
        timestamp: Long,
    ): List<AssetsInfo> {
        var cursor = ""
        val result = mutableListOf<AssetsInfo>()
        val tokenIdsFormatted = tokenIds.joinToString(
            separator = ",",
        ) { "\"$it\"" }
        while (true) {
            val response = networkClient.createJsonRequest<SoraWalletAssetsCase0Response>(
                url,
                HttpMethod.Post,
                SubQueryRequest(graphQLRequestSoraWalletAssetsCase0(cursor, tokenIdsFormatted, timestamp.toString())),
            )
            response.data.entities.nodes.forEach { node ->
                val prevPrice = node.periods.nodes.lastOrNull()?.price?.open?.toDoubleNan()
                result.add(AssetsInfo(node.id, node.liquidity, prevPrice))
            }
            if (response.data.entities.pageInfo.hasNextPage && response.data.entities.pageInfo.endCursor != null) {
                cursor = response.data.entities.pageInfo.endCursor
            } else {
                break
            }
        }
        return result
    }
}
