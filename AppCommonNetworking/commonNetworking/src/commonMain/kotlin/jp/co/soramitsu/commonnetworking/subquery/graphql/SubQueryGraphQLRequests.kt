package jp.co.soramitsu.commonnetworking.subquery.graphql

internal fun referrerRewardsGraphQLRequest(
    address: String,
) = """
    query {
        referrerRewards(
          filter: { referrer: {equalTo: "$address"} }
        ) {
            groupedAggregates(groupBy: REFERRAL) {
                keys sum {amount}
            }
        }
    }
""".trimIndent()

internal fun sbApyGraphQLRequest() = """ query {
                poolXYKEntities (
                    first: 1
                    orderBy: UPDATED_DESC
                  )
                  {
                    nodes {
                      pools {
                        edges {
                          node {
                            targetAssetId,
                            priceUSD,
                            strategicBonusApy
                          }
                        }
                      }
                    }
                  }
                }
                    """.trimIndent()

internal fun soraHistoryGraphQLRequest(
    countRemote: Int,
    myAddress: String,
    cursor: String,
) = """
                    query { 
                      historyElements( 
                        first: $countRemote 
                        orderBy: TIMESTAMP_DESC 
                        after: "$cursor" 
                        filter: { 
                          or: [ 
                            { 
                              address: { equalTo: "$myAddress" } 
                              or: [
                                { module: { equalTo: "assets" } method: { equalTo: "transfer" }} 
                                { module: { equalTo: "liquidityProxy" } method: { equalTo: "swap" }} 
                                { module: { equalTo: "poolXYK" } method: { equalTo: "depositLiquidity" }} 
                                { data: { contains: [{ method: "depositLiquidity" }] }} 
                                { module: { equalTo: "poolXYK" } method: { equalTo: "withdrawLiquidity" }} 
                                { data: { contains: [{ method: "withdrawLiquidity" }] }} 
                              ] 
                            } 
                            { 
                              data: { contains: { to: "$myAddress" } }
                              module: { equalTo: "assets" } method: { equalTo: "transfer" }
                              execution: { contains: { success: true } }
                            } 
                          ] 
                        } 
                      ) { 
                        nodes { 
                          id
                          blockHash
                          module
                          method
                          address
                          networkFee
                          execution
                          timestamp
                          data 
                        }
                        pageInfo {
                          endCursor
                          hasNextPage
                        }
                      } 
                    }
                """.trimIndent()
