package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.adapters

import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.ReferrerRewardFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.SbApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraReferrerRewardsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraSbApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiDAOException
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class SoraSbApyFetcherTest {

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

    private val sbApyFetcher: SbApyFetcher =
        SoraSbApyFetcher(
            apolloClientStore = apolloClientStore,
            configDAO = configDAO
        )

    @Test
    fun `TEST soraSbApyFetcher_fetch EXPECT ExternalApiDAOException_NullUrl BECAUSE stakingUrl is null`() =
        runTest {
            // Mock Preparation Start
            coEvery {
                configDAO.stakingUrl(
                    chainId = chainId
                )
            }.throws(ExternalApiDAOException.NullUrl(chainId))
            // Mock Preparation End

            assertFailsWith<ExternalApiDAOException.NullUrl> {
                sbApyFetcher.fetch(
                    chainId = chainId
                )
            }
        }
}