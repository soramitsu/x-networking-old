package jp.co.soramitsu.xnetworking.fearlesswallet.common.interactors.txhistory.impl

import com.apollographql.apollo3.api.Optional
import jp.co.soramitsu.xnetworking.basic.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.basic.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.basic.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.basic.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.basic.engines.apollo.api.ApolloClientStore
import jp.co.soramitsu.xnetworking.basic.engines.apollo.impl.utils.getPrimitiveContentOrEmpty
import jp.co.soramitsu.xnetworking.basic.engines.apollo.impl.utils.asJsonArrayNullable
import jp.co.soramitsu.xnetworking.basic.engines.apollo.impl.utils.asJsonObjectNullable
import jp.co.soramitsu.xnetworking.fearlesswallet.GetHistoryElementsQuery
import jp.co.soramitsu.xnetworking.fearlesswallet.type.HistoryElementsOrderBy

class FearlessHistoryInfoRemoteLoader(
    private val apolloClientStore: ApolloClientStore
): HistoryInfoRemoteLoader {

    override suspend fun loadHistoryInfo(
        pageCount: Int,
        cursor: String,
        signAddress: String
    ): TxHistoryInfo {
        val response = apolloClientStore.getClient(ApolloClientStore.FEARLESS_SUBQUERY_TAG)?.run {
            query(
                GetHistoryElementsQuery(
                    pageCount = pageCount,
                    cursor = cursor,
                    address = signAddress,
                    orderBy = Optional.present(listOf(HistoryElementsOrderBy.TIMESTAMP_DESC)),
                )
            ).execute().apply {
                exception?.let { throw it }
            }.data?.historyElements
                ?: throw IllegalStateException("GetHistoryElementsQuery response is null")
        } ?: throw IllegalStateException("No Apollo Client is available")

        // Unparsing JSON scalar and normally typed contents
        val items =
            response.nodes.filterNotNull().map {
                val wasOperationSuccessful = it.execution.asJsonObjectNullable
                    ?.getPrimitiveContentOrEmpty("success").toBoolean()

                val rewards = it.data?.asJsonObjectNullable?.get("reward")
                    ?.asJsonArrayNullable?.mapNotNull { json -> json.asJsonObjectNullable }
                    ?.map { jsonObject ->
                        listOf(
                            TxHistoryItemParam(
                                "amount",
                                jsonObject.getPrimitiveContentOrEmpty("amount")
                            ),
                            TxHistoryItemParam(
                                "era",
                                jsonObject.getPrimitiveContentOrEmpty("era")
                            ),
                            TxHistoryItemParam(
                                "isReward",
                                jsonObject.getPrimitiveContentOrEmpty("isReward")
                            ),
                            TxHistoryItemParam(
                                "validator",
                                jsonObject.getPrimitiveContentOrEmpty("validator")
                            ),
                        )
                    }

                val transfers = it.data?.asJsonObjectNullable?.get("transfers")
                    ?.asJsonArrayNullable?.mapNotNull { json -> json.asJsonObjectNullable }
                    ?.map { jsonObject ->
                        listOf(
                            TxHistoryItemParam(
                                "block",
                                jsonObject.getPrimitiveContentOrEmpty("block")
                            ),
                            TxHistoryItemParam(
                                "amount",
                                jsonObject.getPrimitiveContentOrEmpty("amount")
                            ),
                            TxHistoryItemParam(
                                "fee",
                                jsonObject.getPrimitiveContentOrEmpty("fee")
                            ),
                            TxHistoryItemParam(
                                "to",
                                jsonObject.getPrimitiveContentOrEmpty("to")
                            ),
                            TxHistoryItemParam(
                                "from",
                                jsonObject.getPrimitiveContentOrEmpty("from")
                            ),
                            TxHistoryItemParam(
                                "extrinsicHash",
                                jsonObject.getPrimitiveContentOrEmpty("extrinsicHash")
                            ),
                            TxHistoryItemParam(
                                "success",
                                jsonObject.getPrimitiveContentOrEmpty("success")
                            )
                        )
                    }

                val extrinsics = it.data?.asJsonObjectNullable?.get("extrinsic")
                    ?.asJsonArrayNullable?.mapNotNull { json -> json.asJsonObjectNullable }
                    ?.map { jsonObject ->
                        listOf(
                            TxHistoryItemParam(
                                "call",
                                jsonObject.getPrimitiveContentOrEmpty("call")
                            ),
                            TxHistoryItemParam(
                                "fee",
                                jsonObject.getPrimitiveContentOrEmpty("fee")
                            ),
                            TxHistoryItemParam(
                                "hash",
                                jsonObject.getPrimitiveContentOrEmpty("hash")
                            ),
                            TxHistoryItemParam(
                                "module",
                                jsonObject.getPrimitiveContentOrEmpty("module")
                            ),
                            TxHistoryItemParam(
                                "success",
                                jsonObject.getPrimitiveContentOrEmpty("success")
                            ),
                        )
                    }

                TxHistoryItem(
                    id = it.id,
                    blockHash = "",
                    module = when {
                        rewards != null -> "reward"
                        transfers != null -> "transfer"
                        extrinsics != null -> "extrinsic"
                        else -> ""
                    },
                    method = "",
                    timestamp = it.timestamp.toString(),
                    networkFee = "",
                    success = wasOperationSuccessful,
                    nestedData = null,
                    data = when {
                        rewards != null -> rewards.flatten()
                        transfers != null -> transfers.flatten()
                        extrinsics != null -> extrinsics.flatten()
                        else -> null
                    },
                )
            }

        return TxHistoryInfo(
            endCursor = response.pageInfo.endCursor,
            endReached = response.pageInfo.hasNextPage,
            items = items
        )
    }

}