package jp.co.soramitsu.xnetworking.lib.datasources.staking.validators.adapters.subsquid

import jp.co.soramitsu.xnetworking.lib.datasources.staking.api.adapters.ValidatorsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.validators.adapters.subsquid.SubSquidValidatorsFetcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class SubSquidValidatorsFetcherTest {

    private val fetcher: ValidatorsFetcher = SubSquidValidatorsFetcher()

    @Test
    fun `TEST subsquidValidatorsFetcher_fetch EXPECT success`() = runTest {
        val result = fetcher.fetch(
            chainId = "polkadot",
            stashAccountAddress = "",
            historicalRange = emptyList()
        )

        assertContentEquals(emptyList(), result)
    }
}