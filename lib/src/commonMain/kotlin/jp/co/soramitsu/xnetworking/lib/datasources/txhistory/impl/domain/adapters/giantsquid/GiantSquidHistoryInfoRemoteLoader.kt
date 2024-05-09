package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.giantsquid

import jp.co.soramitsu.xnetworking.core.datasources.chainsconfig.api.ChainsConfigFetcher
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.HistoryInfoRemoteLoader
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryInfo
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItem
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.api.models.TxHistoryItemParam
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.GraphQLResponseDataWrapper
import jp.co.soramitsu.xnetworking.core.engines.rest.api.RestClient

class GiantSquidHistoryInfoRemoteLoader(
    private val chainsConfigFetcher: ChainsConfigFetcher,
    private val restClient: RestClient
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
        val requestUrl = requireNotNull(config?.externalApi?.history?.url) {
            "Url for GiantSquid blockExplorer on chain with id - $chainId - is null."
        }

        val response = restClient.post(
            request = GiantSquidRequest(
                url = requestUrl,
                address = signAddress
            ),
            kSerializer = GraphQLResponseDataWrapper.serializer(
                GiantSquidResponse.serializer()
            )
        )

        val items = mutableListOf<TxHistoryItem>()

        response.data.transfers?.map { transferWrapper ->
            TxHistoryItem(
                id = transferWrapper.id,
                blockHash = transferWrapper.transfer.blockNumber,
                module = "transfer",
                method = "",
                timestamp = transferWrapper.transfer.timestamp,
                nestedData = emptyList(),
                networkFee = "",
                success = transferWrapper.transfer.success,
                data = listOfNotNull(
                    transferWrapper.transfer.to?.id?.let { to ->
                        TxHistoryItemParam(
                            "to",
                            to
                        )
                    },
                    transferWrapper.transfer.from?.id?.let { from ->
                        TxHistoryItemParam(
                            "from",
                            from
                        )
                    },
                    TxHistoryItemParam(
                        "amount",
                        transferWrapper.transfer.amount
                    ),
                    transferWrapper.transfer.extrinsicHash?.let { hash ->
                        TxHistoryItemParam(
                            "extrinsicHash",
                            hash
                        )
                    }
                )
            )
        }?.toCollection(items)

        response.data.rewards?.map { reward ->
            TxHistoryItem(
                id = reward.id,
                blockHash = reward.blockNumber.toString(),
                module = "reward",
                method = "",
                timestamp = reward.timestamp,
                nestedData = emptyList(),
                networkFee = "0",
                success = true,
                data = listOfNotNull(
                    reward.era?.let { era ->
                        TxHistoryItemParam(
                            "era",
                            era
                        )
                    },
                    reward.account?.id?.let { accountId ->
                        TxHistoryItemParam(
                            "accountId",
                            accountId
                        )
                    },
                    reward.validatorId?.let { validatorId ->
                        TxHistoryItemParam(
                            "validatorId",
                            validatorId
                        )
                    }
                )
            )
        }?.toCollection(items)

        response.data.bonds?.map { bond ->
            TxHistoryItem(
                id = bond.id,
                blockHash = bond.blockNumber,
                module = "bond",
                method = "",
                timestamp = bond.timestamp,
                nestedData = emptyList(),
                networkFee = "0",
                success = bond.success ?: false,
                data = listOfNotNull(
                    TxHistoryItemParam(
                        "amount",
                        bond.amount
                    ),
                    TxHistoryItemParam(
                        "accountId",
                        bond.accountId
                    )
                )
            )
        }?.toCollection(items)

        response.data.slashes?.map { slash ->
            TxHistoryItem(
                id = slash.id,
                blockHash = slash.blockNumber,
                module = "slash",
                method = "",
                timestamp = slash.timestamp,
                nestedData = emptyList(),
                networkFee = "0",
                success = true,
                data = listOfNotNull(
                    TxHistoryItemParam(
                        "era",
                        slash.era
                    ),
                    TxHistoryItemParam(
                        "amount",
                        slash.amount
                    ),
                    TxHistoryItemParam(
                        "accountId",
                        slash.accountId
                    )
                )
            )
        }?.toCollection(items)

        return TxHistoryInfo(
            endCursor = null,
            endReached = true,
            items = items
        )
    }

}