package jp.co.soramitsu.xnetworking.sorawallet.blockexplorerinfo.assets.case0

internal fun graphQLRequestSoraWalletAssetsCase0(
    cursor: String,
    tokenIds: String,
) = """
    query AssetsQuery {
          entities: assets(
            first: 100 
            after: "$cursor"
            filter: {and: [{id:{in:[$tokenIds]}}]}
            ) {
              nodes {
                id
                liquidity
              }         
              pageInfo {
                hasNextPage
                endCursor
              }
            }
    }
""".trimIndent()
