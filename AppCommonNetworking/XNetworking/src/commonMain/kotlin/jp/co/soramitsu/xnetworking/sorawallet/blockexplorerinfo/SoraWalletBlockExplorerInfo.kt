package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkException
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.ReferrerRewardsInfo
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.SoraWalletReferralCases
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SbApyInfo
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SoraWalletSbApyCases
import kotlin.coroutines.cancellation.CancellationException

class SoraWalletBlockExplorerInfo(
    private val networkClient: SoramitsuNetworkClient,
    private var baseUrl: String,
) {

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getSpApy(
        url: String = baseUrl,
        caseName: String,
    ): List<SbApyInfo> {
        val case = SoraWalletSbApyCases.getSbApyCase(caseName)
        return case.getSbApy(url, networkClient)
    }

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getReferrerRewards(
        address: String,
        caseName: String,
        url: String = baseUrl,
    ): ReferrerRewardsInfo {
        val case = SoraWalletReferralCases.getReferralCase(caseName)
        return case.getReferrerInfo(url, address, networkClient)
    }
}
