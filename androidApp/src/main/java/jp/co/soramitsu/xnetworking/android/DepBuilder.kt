package jp.co.soramitsu.xnetworking.android

import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.GraphQLBlockExplorerRepositoryImpl
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.impl.ChainsConfigFetcherImpl
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonGetRequest
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.HistoryInfoRemoteLoaderFacade
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.etherscan.EtherScanHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.giantsquid.GiantSquidHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.oklink.OkLinkHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.reef.ReefHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.sora.SoraHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.subquery.SubQueryHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.subsquid.SubSquidHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.zeta.ZetaHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.engines.apollo.api.ApolloClientStore
import jp.co.soramitsu.xnetworking.lib.engines.apollo.impl.ApolloClientStoreImpl
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.RestClient
import jp.co.soramitsu.xnetworking.lib.engines.rest.api.models.AbstractRestClientConfig
import jp.co.soramitsu.xnetworking.lib.engines.rest.impl.RestClientImpl
import kotlinx.serialization.json.Json

object DepBuilder {

    private val restClient: RestClient = RestClientImpl(
        restClientConfig = object : AbstractRestClientConfig() {
            override fun isLoggingEnabled(): Boolean = true

            override fun getConnectTimeoutMillis(): Long = 30_000L

            override fun getRequestTimeoutMillis(): Long = 30_000L

            override fun getSocketTimeoutMillis(): Long = 30_000L

            override fun getOrCreateJsonConfig(): Json = Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        }
    )

    private val apolloClientStore: ApolloClientStore = ApolloClientStoreImpl()

    private val chainsConfigFetcher: ChainsConfigFetcher = ChainsConfigFetcherImpl(
        restClient = restClient,
        chainsRequest = JsonGetRequest(
            url = "https://raw.githubusercontent.com/soramitsu/shared-features-utils/develop-free/chains/v9/chains_dev.json"
        )
    )

    val historyRemoteLoaderFacade: HistoryInfoRemoteLoader = HistoryInfoRemoteLoaderFacade(
        chainsConfigFetcher = chainsConfigFetcher,
        loaders = mapOf(
            ChainsConfig.ExternalApi.Type.EtherScan to EtherScanHistoryInfoRemoteLoader(
                chainsConfigFetcher = chainsConfigFetcher,
                restClient = restClient,
                apiKeys = mapOf(
                    ChainAssetConstants.EtherScan.chainId to "paste your EtherScan key"
                )
            ),
            ChainsConfig.ExternalApi.Type.GiantSquid to GiantSquidHistoryInfoRemoteLoader(
                chainsConfigFetcher = chainsConfigFetcher,
                restClient = restClient
            ),
            ChainsConfig.ExternalApi.Type.OkLink to OkLinkHistoryInfoRemoteLoader(
                chainsConfigFetcher = chainsConfigFetcher,
                restClient = restClient,
                apiKeys = mapOf(
                    ChainAssetConstants.OkLink.chainId to "paste your OkLink key"
                )
            ),
            ChainsConfig.ExternalApi.Type.Reef to ReefHistoryInfoRemoteLoader(
                chainsConfigFetcher = chainsConfigFetcher,
                restClient = restClient
            ),
            ChainsConfig.ExternalApi.Type.Sora to SoraHistoryInfoRemoteLoader(
                chainsConfigFetcher = chainsConfigFetcher,
                apolloClientStore = apolloClientStore
            ),
            ChainsConfig.ExternalApi.Type.SubSquid to SubSquidHistoryInfoRemoteLoader(
                chainsConfigFetcher = chainsConfigFetcher,
                restClient = restClient
            ),
            ChainsConfig.ExternalApi.Type.SubQuery to SubQueryHistoryInfoRemoteLoader(
                chainsConfigFetcher = chainsConfigFetcher,
                restClient = restClient
            ),
            ChainsConfig.ExternalApi.Type.Zeta to ZetaHistoryInfoRemoteLoader(
                chainsConfigFetcher = chainsConfigFetcher,
                restClient = restClient
            )
        )
    )

    val blockExplorerRepository = GraphQLBlockExplorerRepositoryImpl(
        apolloClientStore = apolloClientStore,
        chainsConfigFetcher = chainsConfigFetcher
    )
}
