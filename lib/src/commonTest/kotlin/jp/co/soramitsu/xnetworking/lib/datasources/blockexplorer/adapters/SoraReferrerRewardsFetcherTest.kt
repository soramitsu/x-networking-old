package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.adapters

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.FiatDataFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.ReferrerRewardFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraFiatDataFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraReferrerRewardsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiDAOException
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class SoraReferrerRewardsFetcherTest {

    private companion object {
        const val chainId = ""
        const val requestUrl = ""

        const val pageCount = 0
        const val cursor = ""

        const val address = ""
    }

    @Mock
    private val apolloClientStore = mock(classOf<ApolloClientStore>())

    @Mock
    private val configDAO = mock(classOf<ConfigDAO>())

    private val referrerRewardsFetcher: ReferrerRewardFetcher =
        SoraReferrerRewardsFetcher(
            apolloClientStore = apolloClientStore,
            configDAO = configDAO
        )

    @Test
    fun `TEST soraAssetsInfoFetcher_fetch EXPECT ExternalApiDAOException_NullUrl BECAUSE stakingUrl is null`() =
        runTest {
            // Mock Preparation Start
            coEvery {
                configDAO.stakingUrl(
                    chainId = chainId
                )
            }.throws(ExternalApiDAOException.NullUrl(chainId))
            // Mock Preparation End

            assertFailsWith<ExternalApiDAOException.NullUrl> {
                referrerRewardsFetcher.fetch(
                    chainId = chainId,
                    address = address
                )
            }
        }
}