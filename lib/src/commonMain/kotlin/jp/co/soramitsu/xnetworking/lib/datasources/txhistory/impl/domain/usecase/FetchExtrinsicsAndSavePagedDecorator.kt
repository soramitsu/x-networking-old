package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.usecase

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.SoraHistoryDBImpl
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
            chainId = chainId,
            offset = dbOffset,
            count = extrinsicsCountPerPage
        )

        dbOffset += extrinsics.size

        return if (extrinsics.size.toLong() >= extrinsicsCountPerPage) {
            PagedExtrinsicsWrapper(
                isEndReached = false,
                items = extrinsics
            )
        } else {
            if (currentSignerInfo.endReached) {
                PagedExtrinsicsWrapper(
                    isEndReached = true,
                    items = extrinsics
                )
            } else {
                val curSignerInfo = fetchExtrinsicsAndSaveUseCase<T>(
                    cursor = currentSignerInfo.oldCursor.orEmpty(),
                    pageCount = extrinsicsCountPerPage,
                    signAddress = signAddress,
                    currentSignerInfo = currentSignerInfo,
                    chainId = chainId,
                    assetId = assetId,
                    filters = filters
                )

                val extrinsicsRemainder = extrinsicsCountPerPage - extrinsics.size

                val moreExtrinsics = soraHistoryDBImpl.getExtrinsics(
                    signAddress = signAddress,
                    chainId = chainId,
                    offset = dbOffset,
                    count = extrinsicsRemainder
                )
                
                dbOffset += moreExtrinsics.size

                PagedExtrinsicsWrapper(
                    isEndReached = curSignerInfo.endReached,
                    items = extrinsics.plus(moreExtrinsics)
                )
            }
        }
    }

}