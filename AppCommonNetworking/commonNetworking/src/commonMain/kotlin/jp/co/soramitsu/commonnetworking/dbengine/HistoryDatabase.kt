package jp.co.soramitsu.commonnetworking.dbengine

import jp.co.soramitsu.commonnetworking.db.ExtrinsicParam
import jp.co.soramitsu.commonnetworking.db.Extrinsics
import jp.co.soramitsu.commonnetworking.db.SignerInfo
import jp.co.soramitsu.commonnetworking.db.SoraHistoryDatabase
import jp.co.soramitsu.commonnetworking.subquery.history.SoraSubqueryResponse
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal class HistoryDatabase(historyDatabaseFactory: DatabaseDriverFactory) {
    private val db = SoraHistoryDatabase(historyDatabaseFactory.createDriver())
    private val dbQuery = db.soraHistoryDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllExtrinsics()
            dbQuery.removeAllSignerInfo()
        }
    }

    internal fun clearAddressData(signAddress: String) {
        dbQuery.transaction {
            dbQuery.removeExtrinsics(signAddress)
            dbQuery.removeSignerInfo(signAddress)
        }
    }

    internal fun getTransfersAddress(query: String): List<String> {
        return dbQuery.selectTransfersPeers(query).executeAsList()
    }

    internal fun getSignerInfo(signAddress: String): SignerInfo =
        dbQuery.selectSignerInfo(signAddress).executeAsOneOrNull() ?: SignerInfo(
            signAddress,
            0,
            0,
            null,
            false
        )

    internal fun insertSignerInfo(info: SignerInfo) = dbQuery.insertSignerInfoFull(info)

    internal fun getExtrinsics(signAddress: String, offset: Long, count: Int): List<Extrinsics> {
        return dbQuery.selectExtrinsicsPaged(signAddress, count.toLong(), offset).executeAsList()
    }

    internal fun getExtrinsic(signAddress: String, txHash: String): Extrinsics? {
        return dbQuery.selectExtrinsic(txHash, signAddress).executeAsOneOrNull()
    }

    internal fun getExtrinsicParams(extrinsicHash: String): List<ExtrinsicParam> {
        return dbQuery.selectExtrinsicParams(extrinsicHash).executeAsList()
    }

    internal fun getExtrinsicNested(extrinsicHash: String): List<Extrinsics> {
        return dbQuery.selectExtrinsicsNested(extrinsicHash).executeAsList()
    }

    internal fun insertExtrinsics(signAddress: String, response: SoraSubqueryResponse): SignerInfo {
        dbQuery.transaction {
            response.data.historyElements.nodes.forEach { historyResponseItem ->
                val time = historyResponseItem.timestamp.toLong()
                dbQuery.insertExtrinsic(
                    txHash = historyResponseItem.id,
                    signAddress = signAddress,
                    blockHash = historyResponseItem.blockHash,
                    module = historyResponseItem.module,
                    method = historyResponseItem.method,
                    timestamp = time,
                    networkFee = historyResponseItem.networkFee,
                    success = historyResponseItem.execution.success,
                    batch = historyResponseItem.data is JsonArray,
                    parentHash = null,
                )
                if (historyResponseItem.data is JsonObject) {
                    historyResponseItem.data.map {
                        dbQuery.insertExtrinsicParam(
                            historyResponseItem.id,
                            it.key,
                            (it.value as JsonPrimitive).content
                        )
                    }
                }
                if (historyResponseItem.data is JsonArray) {
                    historyResponseItem.data.filterIsInstance<JsonObject>().map { json ->
                        val hash = (json["hash"] as JsonPrimitive).content
                        dbQuery.insertExtrinsic(
                            txHash = hash,
                            signAddress = signAddress,
                            blockHash = historyResponseItem.blockHash,
                            module = (json["module"] as JsonPrimitive).content,
                            method = (json["method"] as JsonPrimitive).content,
                            timestamp = time,
                            networkFee = "",
                            success = historyResponseItem.execution.success,
                            batch = false,
                            parentHash = historyResponseItem.id
                        )
                        ((json["data"] as JsonObject)["args"] as JsonObject).map { nested ->
                            dbQuery.insertExtrinsicParam(
                                hash,
                                nested.key,
                                (nested.value as JsonPrimitive).content
                            )
                        }
                    }
                }
            }
        }
        return SignerInfo(
            signAddress = signAddress,
            topTime = response.data.historyElements.nodes.firstOrNull()?.timestamp?.toLong() ?: 0,
            oldTime = response.data.historyElements.nodes.lastOrNull()?.timestamp?.toLong() ?: 0,
            oldCursor = response.data.historyElements.pageInfo.endCursor,
            endReached = response.data.historyElements.pageInfo.hasNextPage.not(),
        )
    }
}