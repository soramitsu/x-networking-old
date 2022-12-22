package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral

import io.ktor.http.HttpMethod
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.BasicCases
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

internal object SoraWalletReferralCases : BasicCases<SoraWalletReferralCase>() {

    override fun provideInstance(caseName: String): SoraWalletReferralCase {
        return when (caseName) {
            "0" -> SoraWalletReferralCase0()
            "1" -> SoraWalletReferralCase1()
            else -> throw IllegalArgumentException("SoraWalletReferralCases [$caseName] not found")
        }
    }
}

private class SoraWalletReferralCase0 : SoraWalletReferralCase {

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

private class SoraWalletReferralCase1 : SoraWalletReferralCase {

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
