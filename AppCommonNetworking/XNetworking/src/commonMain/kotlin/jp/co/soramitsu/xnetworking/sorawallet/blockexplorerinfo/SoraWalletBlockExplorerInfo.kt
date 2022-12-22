package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkException
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.fiat.FiatData
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.fiat.SoraWalletFiatCases
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.ReferrerRewardsInfo
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.referral.SoraWalletReferralCases
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SbApyInfo
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.SoraWalletSbApyCases
import kotlin.coroutines.cancellation.CancellationException

class SoraWalletBlockExplorerInfo(
    private val networkClient: SoramitsuNetworkClient,
    private val baseUrl: String,
) {

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getFiat(caseName: String): List<FiatData> {
        val case = SoraWalletFiatCases.getCase(caseName)
        return case.getFiat(baseUrl, networkClient)
    }

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getSpApy(caseName: String): List<SbApyInfo> {
        val case = SoraWalletSbApyCases.getCase(caseName)
        return case.getSbApy(baseUrl, networkClient)
    }

    @Throws(
        SoramitsuNetworkException::class,
        CancellationException::class,
        IllegalArgumentException::class
    )
    suspend fun getReferrerRewards(
        address: String,
        caseName: String,
    ): ReferrerRewardsInfo {
        val case = SoraWalletReferralCases.getCase(caseName)
        return case.getReferrerInfo(baseUrl, address, networkClient)
    }
}
