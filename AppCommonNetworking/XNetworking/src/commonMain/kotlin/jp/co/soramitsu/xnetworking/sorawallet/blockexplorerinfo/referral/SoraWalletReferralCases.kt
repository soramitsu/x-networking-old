package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral

import io.ktor.http.HttpMethod
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case0.SoraWalletReferrerCase0Response
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case0.graphQLRequestSoraWalletReferrerCase0
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case1.SoraWalletReferrerCase1Response
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.case1.graphQLRequestSoraWalletReferrerCase1
import jp.co.soramitsu.xnetworking.txhistory.subquery.graphqlrequest.SubQueryRequest

internal interface SoraWalletReferralCase {
    suspend fun getReferrerInfo(
        url: String,
        address: String,
        networkClient: SoramitsuNetworkClient,
    ): ReferrerRewardsInfo
}

/**
 * sora wallet prod 06 09 2022 https://api.subquery.network/sq/sora-xor/sora, https://subquery.q1.sora2.soramitsu.co.jp
 * sora wallet stage 06 09 2022 https://api.subquery.network/sq/sora-xor/sora-staging, https://subquery.q1.stg1.sora2.soramitsu.co.jp
 */
internal class SoraWalletReferralCase0 : SoraWalletReferralCase {

    override suspend fun getReferrerInfo(
        url: String,
        address: String,
        networkClient: SoramitsuNetworkClient,
    ): ReferrerRewardsInfo {
        val response = networkClient.createJsonRequest<SoraWalletReferrerCase0Response>(
            url,
            HttpMethod.Post,
            SubQueryRequest(graphQLRequestSoraWalletReferrerCase0(address))
        )
        return ReferrerRewardsInfo(response.data.referrerRewards.groupedAggregates.map {
            ReferrerReward(it.keys.firstOrNull() ?: "", it.sum.amount)
        })
    }
}

/**
 * sora wallet dev 06 09 2022 https://api.subquery.network/sq/sora-xor/sora-dev
 */
internal class SoraWalletReferralCase1 : SoraWalletReferralCase {

    override suspend fun getReferrerInfo(
        url: String,
        address: String,
        networkClient: SoramitsuNetworkClient,
    ): ReferrerRewardsInfo {
        var cursor = ""
        val list = mutableMapOf<String, String>()
        while (true) {
            val response = networkClient.createJsonRequest<SoraWalletReferrerCase1Response>(
                url,
                HttpMethod.Post,
                SubQueryRequest(graphQLRequestSoraWalletReferrerCase1(address, cursor))
            )
            response.data.referrerRewards.nodes.forEach {
                list[it.referral] = it.amount
            }
            if (response.data.referrerRewards.pageInfo.hasNextPage && response.data.referrerRewards.pageInfo.endCursor != null) {
                cursor = response.data.referrerRewards.pageInfo.endCursor
            } else {
                break
            }
        }
        return ReferrerRewardsInfo(list.map {
            ReferrerReward(it.key, it.value)
        })
    }
}

internal object SoraWalletReferralCases {

    fun getReferralCase(
        caseName: String,
    ): SoraWalletReferralCase {
        return when (caseName) {
            "0" -> SoraWalletReferralCase0()
            "1" -> SoraWalletReferralCase1()
            else -> throw IllegalArgumentException("SoraWalletReferralCases [$caseName] not found")
        }
    }
}
