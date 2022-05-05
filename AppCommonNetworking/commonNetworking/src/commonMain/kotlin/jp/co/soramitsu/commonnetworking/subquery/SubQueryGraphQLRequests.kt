package jp.co.soramitsu.commonnetworking.subquery

internal fun referrerRewardsGraphQLRequest(
    count: Int,
    address: String,
    cursor: String = "",
) = """
    query {
        referrerRewards(
          first: $count
          orderBy: TIMESTAMP_DESC
          after: "$cursor"
          filter: { referral: {equalTo: "$address"}}
        ) {
        pageInfo {
          hasNextPage
          endCursor
        }
        nodes {
          id
          blockHeight
          referrer
          amount
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
    assetId: String = ""
) = if (assetId.isEmpty()) {
    """
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
} else {
    """
                    query {
                      historyElements(
                        first: $countRemote
                        orderBy: TIMESTAMP_DESC
                        after: "$cursor" 
                        filter: {
                          or: [
                            {
                              address: {
                                equalTo: "$myAddress"
                              }
                              or: [
                                {
                                  module: { equalTo: "assets" }
                                  method: { equalTo: "transfer" }
                                  data: {
                                    contains: {
                                      assetId: "$assetId"
                                    }
                                  }
                                }
                                {
                                  module: { equalTo: "liquidityProxy" }
                                  method: { equalTo: "swap" }
                                  or: [
                                    {
                                      data: {
                                        contains: {
                                          baseAssetId: "$assetId"
                                        }
                                      }
                                    }
                                    {
                                      data: {
                                        contains: {
                                          targetAssetId: "$assetId"
                                        }
                                      }
                                    }
                                  ]
                                }
                                {
                                  module: { equalTo: "poolXYK" }
                                  method: { equalTo: "depositLiquidity" }
                                  or: [
                                    {
                                      data: {
                                        contains: {
                                          baseAssetId: "$assetId"
                                        }
                                      }
                                    }
                                    {
                                      data: {
                                        contains: {
                                          targetAssetId: "$assetId"
                                        }
                                      }
                                    }
                                  ]
                                }
                                {
                                  data: { contains: [{ method: "depositLiquidity" }] }
                                  and: {
                                    or: [
                                      {
                                        data: {
                                          contains: {
                                            baseAssetId: "$assetId"
                                          }
                                        }
                                      }
                                      {
                                        data: {
                                          contains: {
                                            targetAssetId: "$assetId"
                                          }
                                        }
                                      }
                                    ]
                                  }
                                }
                                {
                                  module: { equalTo: "poolXYK" }
                                  method: { equalTo: "withdrawLiquidity" }
                                  or: [
                                    {
                                      data: {
                                        contains: {
                                          baseAssetId: "$assetId"
                                        }
                                      }
                                    }
                                    {
                                      data: {
                                        contains: {
                                          targetAssetId: "$assetId"
                                        }
                                      }
                                    }
                                  ]
                                }
                                {
                                  data: { contains: [{ method: "withdrawLiquidity" }] }
                                  and: {
                                    or: [
                                      {
                                        data: {
                                          contains: {
                                            baseAssetId: "$assetId"
                                          }
                                        }
                                      }
                                      {
                                        data: {
                                          contains: {
                                            targetAssetId: "$assetId"
                                          }
                                        }
                                      }
                                    ]
                                  }
                                }
                                {
                                  module: { equalTo: "utility" }
                                  method: { equalTo: "batchAll" }
                                  or: [
                                    {
                                      and: [
                                        { data: { contains: [{ method: "register" }] } }
                                        {
                                          or: [
                                            {
                                              data: {
                                                contains: [
                                                  {
                                                    data: {
                                                      args: {
                                                        base_asset_id: "$assetId"
                                                      }
                                                    }
                                                  }
                                                ]
                                              }
                                            }
                                            {
                                              data: {
                                                contains: [
                                                  {
                                                    data: {
                                                      args: {
                                                        target_asset_id: "$assetId"
                                                      }
                                                    }
                                                  }
                                                ]
                                              }
                                            }
                                          ]
                                        }
                                      ]
                                    }
                                    {
                                      and: [
                                        { data: { contains: [{ method: "depositLiquidity" }] } }
                                        {
                                          or: [
                                            {
                                              data: {
                                                contains: [
                                                  {
                                                    data: {
                                                      args: {
                                                        input_asset_a: "$assetId"
                                                      }
                                                    }
                                                  }
                                                ]
                                              }
                                            }
                                            {
                                              data: {
                                                contains: [
                                                  {
                                                    data: {
                                                      args: {
                                                        input_asset_a: "$assetId"
                                                      }
                                                    }
                                                  }
                                                ]
                                              }
                                            }
                                          ]
                                        }
                                      ]
                                    }
                                    {
                                      and: [
                                        { data: { contains: [{ method: "initializePool" }] } }
                                        {
                                          or: [
                                            {
                                              data: {
                                                contains: [
                                                  {
                                                    data: {
                                                      args: {
                                                        asset_a: "$assetId"
                                                      }
                                                    }
                                                  }
                                                ]
                                              }
                                            }
                                            {
                                              data: {
                                                contains: [
                                                  {
                                                    data: {
                                                      args: {
                                                        asset_b: "$assetId"
                                                      }
                                                    }
                                                  }
                                                ]
                                              }
                                            }
                                          ]
                                        }
                                      ]
                                    }
                                  ]
                                }
                              ]
                            }
                            {
                              data: {
                                contains: {
                                  to: "$myAddress"
                                }
                              }
                              and: {
                                data: {
                                  contains: {
                                    assetId: "$assetId"
                                  }
                                }
                              }
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
}
