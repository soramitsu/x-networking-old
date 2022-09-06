package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case1

internal fun graphQLRequestSoraWalletSbApyCase1() = """ query
                FiatPriceQuery {
                    poolXYKs {
                          nodes {
                            id priceUSD strategicBonusApy
                          }
                    }
                }
                    """.trimIndent()
