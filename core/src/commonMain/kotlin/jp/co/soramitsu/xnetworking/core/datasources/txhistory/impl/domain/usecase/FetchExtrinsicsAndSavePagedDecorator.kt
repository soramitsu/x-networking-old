package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.usecase

import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.SoraHistoryDBImpl
import jp.co.soramitsu.xnetworking.db.Extrinsics
import jp.co.soramitsu.xnetworking.db.SignerInfo

internal class FetchExtrinsicsAndSavePagedDecorator(
    private val fetchExtrinsicsAndSaveUseCase: FetchExtrinsicsAndSaveUseCase,
    private val soraHistoryDBImpl: SoraHistoryDBImpl,
) {

    class PagedExtrinsicsWrapper(
        val isEndReached: Boolean,
        val items: List<Extrinsics>
    )

    suspend operator fun <T> invoke(
        signAddress: String,
        networkName: String,
        page: Long,
        currentSignerInfo: SignerInfo,
        extrinsicsCountPerPage: Int,
        chainId: String,
        assetId: String,
        filters: Set<TxFilter>
    ): PagedExtrinsicsWrapper {
        var dbOffset = (page - 1) * extrinsicsCountPerPage

        val extrinsics = soraHistoryDBImpl.getExtrinsics(
            signAddress = signAddress,
            networkName = networkName,
            offset = dbOffset,
            count = extrinsicsCountPerPage
        )

        dbOffset += extrinsics.size

        if (extrinsics.size.toLong() >= extrinsicsCountPerPage) {
            return PagedExtrinsicsWrapper(
                isEndReached = false,
                items = extrinsics
            )
        } else {
            if (currentSignerInfo.endReached) {
                return PagedExtrinsicsWrapper(
                    isEndReached = true,
                    items = extrinsics
                )
            } else {
                val curSignerInfo = fetchExtrinsicsAndSaveUseCase<T>(
                    cursor = currentSignerInfo.oldCursor.orEmpty(),
                    signAddress = signAddress,
                    networkName = networkName,
                    currentSignerInfo = currentSignerInfo,
                    chainId = chainId,
                    assetId = assetId,
                    filters = filters
                )

                val extrinsicsRemainder = extrinsicsCountPerPage - extrinsics.size

                val moreExtrinsics = soraHistoryDBImpl.getExtrinsics(
                    signAddress = signAddress,
                    networkName = networkName,
                    offset = dbOffset,
                    count = extrinsicsRemainder
                )
                
                dbOffset += moreExtrinsics.size

                return PagedExtrinsicsWrapper(
                    isEndReached = curSignerInfo.endReached,
                    items = extrinsics.plus(moreExtrinsics)
                )
            }
        }
    }

}