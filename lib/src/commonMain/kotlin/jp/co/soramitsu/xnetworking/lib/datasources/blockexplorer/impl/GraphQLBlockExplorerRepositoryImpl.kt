package jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.impl

import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.BlockExplorerRepository
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.AssetsInfoResponse
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.FiatDataResponse
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.ReferrerRewardResponse
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.api.models.SbApyInfoResponse
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.impl.usecase.GetAssetsInfoUseCase
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.impl.usecase.GetFiatDataUseCase
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.impl.usecase.GetReferrerRewardsUseCase
import jp.co.soramitsu.xnetworking.core.datasources.blockexplorer.impl.usecase.GetSbApyInfoUseCase
import jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api.models.ChainsConfig
import jp.co.soramitsu.xnetworking.core.engines.apollo.api.ApolloClientStore

class GraphQLBlockExplorerRepositoryImpl(
    apolloClientStore: ApolloClientStore,
    private val chainsConfigFetcher: ChainsConfigFetcher
): BlockExplorerRepository {

    private val getAssetsInfoUseCase = GetAssetsInfoUseCase(apolloClientStore)
    private val getFiatDataUseCase = GetFiatDataUseCase(apolloClientStore)
    private val getReferrerRewardsUseCase = GetReferrerRewardsUseCase(apolloClientStore)
    private val getSbApyInfoUseCase = GetSbApyInfoUseCase(apolloClientStore)

    override suspend fun getAssetsInfo(
        chainId: String,
        tokenIds: List<String>,
        timeStamp: Int
    ): List<AssetsInfoResponse> {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val type = config?.externalApi?.history?.type
        val requestUrl = config?.externalApi?.history?.url

        if (type == null || requestUrl == null)
            return emptyList()

        check(type === ChainsConfig.ExternalApi.Type.Sora) {
            "BlockExplorerRepository::getAssetsInfo operation is not available for chains other than Sora"
        }

        return getAssetsInfoUseCase.invoke(
            requestUrl,
            tokenIds,
            timeStamp
        )
    }

    override suspend fun getFiat(
        chainId: String
    ): List<FiatDataResponse> {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val type = config?.externalApi?.history?.type
        val requestUrl = config?.externalApi?.history?.url

        if (type == null || requestUrl == null)
            return emptyList()

        check(type === ChainsConfig.ExternalApi.Type.Sora) {
            "BlockExplorerRepository::getFiat operation is not available for chains other than Sora"
        }

        return getFiatDataUseCase.invoke(requestUrl)
    }

    override suspend fun getReferrerRewards(
        chainId: String,
        address: String
    ): List<ReferrerRewardResponse> {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val type = config?.externalApi?.history?.type
        val requestUrl = config?.externalApi?.history?.url

        if (type == null || requestUrl == null)
            return emptyList()

        check(type === ChainsConfig.ExternalApi.Type.Sora) {
            "BlockExplorerRepository::getReferrerRewards operation is not available for chains other than Sora"
        }

        return getReferrerRewardsUseCase.invoke(requestUrl, address)
    }

    override suspend fun getSbApyInfo(
        chainId: String
    ): List<SbApyInfoResponse> {
        val config = chainsConfigFetcher.loadConfigOrGetCached()[chainId]
        val type = config?.externalApi?.history?.type
        val requestUrl = config?.externalApi?.history?.url

        if (type == null || requestUrl == null)
            return emptyList()

        check(type === ChainsConfig.ExternalApi.Type.Sora) {
            "BlockExplorerRepository::getSbApyInfo operation is not available for chains other than Sora"
        }

        return getSbApyInfoUseCase.invoke(requestUrl)
    }

}