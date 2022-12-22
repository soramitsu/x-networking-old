package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.sbapy.case2

internal fun graphQLRequestSoraWalletSbApyCase2() = """ query
                FiatPriceQuery {
                    poolXYKs {
                          nodes {
                            id strategicBonusApy
                          }
                    }
                }
                    """.trimIndent()
