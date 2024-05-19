package jp.co.soramitsu.xnetworking.android

import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.BlockExplorerRepositoryImpl
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraAssetsInfoFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraFiatDataFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraReferrerRewardsFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl.adapters.SoraSbApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.data.ConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiType
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.impl.data.InMemorySavingConfigFetcherImpl
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.impl.FearlessConfigDAOImpl
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.adapters.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.HistoryInfoRemoteLoaderFacade
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.etherscan.EtherScanHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.giantsquid.GiantSquidHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.oklink.OkLinkHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.reef.ReefHistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.sorasubquery.SoraSubQueryHistoryInfoRemoteLoader
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

    init {
    }

    private val apolloClientStore: ApolloClientStore = ApolloClientStoreImpl()

    val configFetcher: ConfigFetcher = InMemorySavingConfigFetcherImpl(
        restClient = restClient,
        chainsRequestUrl = "https://raw.githubusercontent.com/soramitsu/shared-features-utils/develop-free/chains/v9/chains_dev.json"
    )

    private val configDAO: ConfigDAO = FearlessConfigDAOImpl(configFetcher)

    val historyRemoteLoaderFacade: HistoryInfoRemoteLoader = HistoryInfoRemoteLoaderFacade(
        configDAO = configDAO,
        loaders = mapOf(
            ExternalApiType.ETHERSCAN to EtherScanHistoryInfoRemoteLoader(
                configDAO = configDAO,
                restClient = restClient,
                apiKeys = mapOf(
                    ChainAssetConstants.EtherScan.chainId to "paste your EtherScan key"
                )
            ),
            ExternalApiType.GIANTSQUID to GiantSquidHistoryInfoRemoteLoader(
                configDAO = configDAO,
                restClient = restClient
            ),
            ExternalApiType.OKLINK to OkLinkHistoryInfoRemoteLoader(
                configDAO = configDAO,
                restClient = restClient,
                apiKeys = mapOf(
                    ChainAssetConstants.OkLink.chainId to "paste your OkLink key"
                )
            ),
            ExternalApiType.REEF to ReefHistoryInfoRemoteLoader(
                configDAO = configDAO,
                restClient = restClient
            ),
            ExternalApiType.SORA to SoraSubQueryHistoryInfoRemoteLoader(
                configDAO = configDAO,
                apolloClientStore = apolloClientStore
            ),
            ExternalApiType.SUBSQUID to SubSquidHistoryInfoRemoteLoader(
                configDAO = configDAO,
                restClient = restClient
            ),
            ExternalApiType.SUBQUERY to SubQueryHistoryInfoRemoteLoader(
                configDAO = configDAO,
                restClient = restClient
            ),
            ExternalApiType.ZETA to ZetaHistoryInfoRemoteLoader(
                configDAO = configDAO,
                restClient = restClient
            )
        )
    )

    val blockExplorerRepository = BlockExplorerRepositoryImpl(
        assetsInfoFetcher = SoraAssetsInfoFetcher(
            apolloClientStore = apolloClientStore,
            configDAO = configDAO
        ),
        fiatDataFetcher = SoraFiatDataFetcher(
            apolloClientStore = apolloClientStore,
            configDAO = configDAO
        ),
        referrerRewardFetcher = SoraReferrerRewardsFetcher(
            apolloClientStore = apolloClientStore,
            configDAO = configDAO
        ),
        sbApyFetcher = SoraSbApyFetcher(
            apolloClientStore = apolloClientStore,
            configDAO = configDAO
        ),
        configDAO = configDAO
    )
}
