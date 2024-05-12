package jp.co.soramitsu.xnetworking.lib.datasources.staking.impl.domain.unbonding.adapters.subsquid

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLSerializableRequestWrapper
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal inline fun SubSquidUnbondingRequest(
    url: String,
    delegatorAddress: String,
    collatorAddress: String
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        """
           query MyQuery {
            historyElements(
              where: {
                staker: {
                  id_eq: "$delegatorAddress"
                }, 
                amount_isNull: false, 
                collator: {
                  id_eq: "$collatorAddress"
                }
              }
            ) {
              id
              amount
              blockNumber
              timestamp
              type
            }
          }
        """.trimIndent()
    ),
)