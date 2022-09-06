package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case0

internal fun graphQLRequestSoraWalletSbApyCase0() = """ query {
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
