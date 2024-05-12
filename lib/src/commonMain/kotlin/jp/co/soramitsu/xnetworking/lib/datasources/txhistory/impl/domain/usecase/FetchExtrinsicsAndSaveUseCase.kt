package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.usecase

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.SoraHistoryDBImpl
import jp.co.soramitsu.xnetworking.db.SignerInfo
import kotlin.math.max
import kotlin.math.min

internal class FetchExtrinsicsAndSaveUseCase(
    private val soraHistoryDBImpl: SoraHistoryDBImpl,
    private val historyInfoRemoteLoader: HistoryInfoRemoteLoader
) {

    suspend operator fun <T> invoke(
        cursor: String,
        pageCount: Int,
        signAddress: String,
        currentSignerInfo: SignerInfo,
        chainId: String,
        assetId: String,
        filters: Set<TxFilter>
    ): SignerInfo {
        val txHistoryInfo = soraHistoryDBImpl.insertExtrinsics(
            signAddress,
            chainId,
            historyInfoRemoteLoader.loadHistoryInfo(
                pageCount = pageCount,
                cursor = cursor,
                signAddress = signAddress,
                chainId = chainId, 
                assetId = assetId,
                filters = filters
            )
        )

        val isCurrentSignerTimestampIsInvalid =
            currentSignerInfo.oldTime == 0L

        val isFinalEndReached = txHistoryInfo.endReached
            || currentSignerInfo.endReached

        val isCurrentSignerCursorInvalid =
            txHistoryInfo.endReached ||
            currentSignerInfo.oldCursor.isNullOrEmpty() ||
            txHistoryInfo.oldTime < currentSignerInfo.oldTime

        return SignerInfo(
            signAddress = signAddress,
            networkName = chainId,
            topTime = max(txHistoryInfo.topTime, currentSignerInfo.topTime),
            oldTime = if (isCurrentSignerTimestampIsInvalid)
                txHistoryInfo.oldTime else min(txHistoryInfo.oldTime, currentSignerInfo.oldTime),
            endReached = isFinalEndReached,
            oldCursor = if (isCurrentSignerCursorInvalid)
                txHistoryInfo.oldCursor else currentSignerInfo.oldCursor
        ).apply { soraHistoryDBImpl.insertSignerInfo(this) }
    }

}