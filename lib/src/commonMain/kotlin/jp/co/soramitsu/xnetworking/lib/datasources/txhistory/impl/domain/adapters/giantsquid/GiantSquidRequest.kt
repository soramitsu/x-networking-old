package jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.adapters.giantsquid

import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.GraphQLSerialiableRequestWrapper
import jp.co.soramitsu.xnetworking.core.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal fun GiantSquidRequest(
    url: String,
    address: String
) = JsonPostRequest(
    url = url,
    body = GraphQLSerialiableRequestWrapper(
        """
            query MyQuery {
              transfers(
                where: {
                  account: {
                    id_eq: "$address"
                  }
                }, 
                orderBy: id_DESC
              ) {
                id
                transfer {
                  amount
                  blockNumber
                  extrinsicHash
                  from {
                    id
                  }
                  to {
                    id
                  }
                  timestamp
                  success
                  id
                }
                direction
              }
            }
        """.trimIndent()
    )
)