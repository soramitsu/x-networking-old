package jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.impl

import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.BlockExplorerRepository
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.AssetsInfoFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.FiatDataFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.ReferrerRewardFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.adapters.SbApyFetcher
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.AssetsInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.FiatDataResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.ReferrerRewardResponse
import jp.co.soramitsu.xnetworking.lib.datasources.blockexplorer.api.models.SbApyInfoResponse
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.ConfigDAO
import jp.co.soramitsu.xnetworking.lib.datasources.chainsconfig.api.models.ExternalApiType

class BlockExplorerRepositoryImpl(
    private val assetsInfoFetcher: AssetsInfoFetcher,
    private val fiatDataFetcher: FiatDataFetcher,
    private val referrerRewardFetcher: ReferrerRewardFetcher,
    private val sbApyFetcher: SbApyFetcher,
    private val configDAO: ConfigDAO
): BlockExplorerRepository() {

    override suspend fun getAssetsInfo(
        chainId: String,
        tokenIds: List<String>,
        timeStamp: Int
    ): List<AssetsInfoResponse> {
        check(configDAO.stakingType(chainId) === ExternalApiType.SORA) {
            "BlockExplorerRepository::getAssetsInfo operation is not available for chains other than Sora"
        }

        return assetsInfoFetcher.fetch(
            chainId,
            tokenIds,
            timeStamp
        )
    }

    override suspend fun getFiat(
        chainId: String
    ): List<FiatDataResponse> {
        check(configDAO.stakingType(chainId) === ExternalApiType.SORA) {
            "BlockExplorerRepository::getFiat operation is not available for chains other than Sora"
        }

        return fiatDataFetcher.fetch(chainId)
    }

    override suspend fun getReferrerRewards(
        chainId: String,
        address: String
    ): List<ReferrerRewardResponse> {
        check(configDAO.stakingType(chainId) === ExternalApiType.SORA) {
            "BlockExplorerRepository::getReferrerRewards operation is not available for chains other than Sora"
        }

        return referrerRewardFetcher.fetch(chainId, address)
    }

    override suspend fun getSbApyInfo(
        chainId: String
    ): List<SbApyInfoResponse> {
        check(configDAO.stakingType(chainId) === ExternalApiType.SORA) {
            "BlockExplorerRepository::getSbApyInfo operation is not available for chains other than Sora"
        }

        return sbApyFetcher.fetch(chainId)
    }

}