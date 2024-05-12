package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters

import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.models.TxHistoryInfo

class HistoryInfoRemoteLoaderFacade(
    private val loaders: Map<ChainsConfig.ExternalApi.Type, HistoryInfoRemoteLoader>,
    private val chainsConfigFetcher: ChainsConfigFetcher,
): HistoryInfoRemoteLoader {
    override suspend fun loadHistoryInfo(
        pageCount: Int,
        cursor: String?,
        signAddress: String,
        chainId: String,
        assetId: String,
        filters: Set<TxFilter>
    ): TxHistoryInfo {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val explorerType = requireNotNull(config?.externalApi?.history?.type) {
            "ExplorerType for chain with id - $chainId - is null."
        }

        val loader = loaders[explorerType]
            ?: error("Remote History Loader could not have been found.")

        return loader.loadHistoryInfo(pageCount, cursor, signAddress, chainId, assetId, filters)
    }
}