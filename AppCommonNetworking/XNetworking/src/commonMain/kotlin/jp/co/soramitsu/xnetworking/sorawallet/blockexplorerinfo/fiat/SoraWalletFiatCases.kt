package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.fiat

import jp.co.soramitsu.xnetworking.networkclient.SoramitsuNetworkClient
import jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.BasicCases

internal interface SoraWalletFiatCase {

    suspend fun getFiat(url: String, networkClient: SoramitsuNetworkClient): List<FiatData>
}

internal object Sfwf : BasicCases<SoraWalletFiatCase>() {
    override fun provideInstance(caseName: String): SoraWalletFiatCase {
        return when (caseName) {
            "0" -> SoraWalletFiatCase0()
            else -> throw IllegalArgumentException("SoraWalletFiatCases [$caseName] not found")
        }
    }
}

internal object SoraWalletFiatCases {

    fun getFiatCase(caseName: String): SoraWalletFiatCase {
        return when (caseName) {
            "0" -> SoraWalletFiatCase0()
            else -> throw IllegalArgumentException("SoraWalletFiatCases [$caseName] not found")
        }
    }
}

private class SoraWalletFiatCase0 : SoraWalletFiatCase {
    override suspend fun getFiat(
        url: String,
        networkClient: SoramitsuNetworkClient
    ): List<FiatData> {
        return emptyList()
    }
}
