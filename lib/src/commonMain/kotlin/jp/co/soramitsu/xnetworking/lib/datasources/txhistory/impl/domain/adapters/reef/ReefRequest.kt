package jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.adapters.reef

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.TxFilter
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.GraphQLSerializableRequestWrapper
import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.impl.domain.JsonPostRequest

@Suppress("FunctionName")
internal inline fun ReefRequest(
    url: String,
    address: String,
    limit: Int,
    cursor: String?,
    txFilter: TxFilter
) = JsonPostRequest(
    url = url,
    body = GraphQLSerializableRequestWrapper(
        when(txFilter) {
            TxFilter.TRANSFER -> transfersRequest(address, limit, cursor)
            TxFilter.EXTRINSIC -> extrinsicsRequest(address, limit, cursor)
            TxFilter.REWARD -> rewardsRequest(address, limit, cursor)
        }
    ),
)

internal inline fun transfersRequest(
    address: String,
    limit: Int,
    cursor: String?
) = """
    query MyQuery {
      transfersConnection(
        where: {
          AND: [
            {
              type_eq: Native
            }, 
            {
              OR: [
                {
                  from: {
                    id_eq: "$address"
                  }
                }, 
                {
                  to: {
                    id_eq: "$address"
                  }
                }
              ]
            }
          ]
        }, 
        orderBy: timestamp_DESC, 
        first: $limit, 
        after: ${cursor?.let { "\"$it\"" }}
      ) {
        edges {
          node {
            id
            amount
            timestamp
            success
            to {
              id
            }
            from {
              id
            }
            signedData
            extrinsicHash
          }
        }
        pageInfo {
          endCursor
          hasNextPage
          startCursor
        }
      }
    }
""".trimIndent()

internal inline fun rewardsRequest(
    address: String,
    limit: Int,
    cursor: String?
) = """
    query MyQuery {
    stakingsConnection(
      orderBy: timestamp_DESC, 
      where: {
        AND: {
          signer: {
            id_eq: "$address"
          }, 
          amount_gt: "0"
        }
      }, 
      first: $limit, 
      after: ${cursor?.let { "\"$it\"" }}
    ) {
        edges {
          node {
            id
            amount
            timestamp
            signer {
              id
            }
          }
        }
        pageInfo {
          endCursor
          hasNextPage
        }
      }
    }
""".trimIndent()

internal inline fun extrinsicsRequest(
    address: String,
    limit: Int,
    cursor: String?
) = """
    query MyQuery {
      extrinsicsConnection(
        orderBy: id_ASC, 
        where: {
          signer_eq: "$address"
        }, 
        first: $limit, 
        after: ${cursor?.let { "\"$it\"" }}
      ) {
        edges {
          node {
            id
            hash
            method
            section
            signedData
            status
            signer
            timestamp
            type
          }
        }
        pageInfo {
          endCursor
          hasNextPage
        }
      }
    }
""".trimIndent()