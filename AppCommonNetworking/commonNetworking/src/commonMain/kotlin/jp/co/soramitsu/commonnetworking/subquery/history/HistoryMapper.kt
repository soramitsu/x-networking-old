package jp.co.soramitsu.commonnetworking.subquery.history

import jp.co.soramitsu.commonnetworking.db.ExtrinsicParam
import jp.co.soramitsu.commonnetworking.db.Extrinsics

object HistoryMapper {

    fun mapParams(extrinsic: Extrinsics, params: List<ExtrinsicParam>): SoraHistoryItem =
        SoraHistoryItem(
            id = extrinsic.txHash,
            blockHash = extrinsic.blockHash.orEmpty(),
            module = extrinsic.module,
            method = extrinsic.method,
            timestamp = extrinsic.timestamp.toString(),
            networkFee = extrinsic.networkFee,
            success = extrinsic.success,
            data = params.map { SoraHistoryItemParam(it.paramName, it.paramValue) },
            nestedData = null,
        )

    fun mapItems(extrinsic: Extrinsics, params: List<SoraHistoryItemNested>): SoraHistoryItem =
        SoraHistoryItem(
            id = extrinsic.txHash,
            blockHash = extrinsic.blockHash.orEmpty(),
            module = extrinsic.module,
            method = extrinsic.method,
            timestamp = extrinsic.timestamp.toString(),
            networkFee = extrinsic.networkFee,
            success = extrinsic.success,
            data = null,
            nestedData = params,
        )

    fun mapItemNested(extrinsic: Extrinsics, params: List<ExtrinsicParam>): SoraHistoryItemNested =
        SoraHistoryItemNested(
            module = extrinsic.module,
            method = extrinsic.method,
            hash = extrinsic.txHash,
            data = params.map { SoraHistoryItemParam(it.paramName, it.paramValue) },
        )
}
