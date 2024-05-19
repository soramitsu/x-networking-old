package jp.co.soramitsu.xnetworking.android

import jp.co.soramitsu.xnetworking.lib.datasources.txhistory.api.adapters.ChainInfo

enum class ChainAssetConstants(val chainId: String, val utilityAssetId: String) {

    EtherScan(
        chainId = "1",
        utilityAssetId = "c2a6c062-d511-4bde-9ce6-ea775d2a302c"
    ),
    GiantSquid(
        chainId = "48239ef607d7928874027a43a67689209727dfb3d3dc5e5b03a39bdc2eda771a",
        utilityAssetId = "1e0c2ec6-935f-49bd-a854-5e12ee6c9f1b"
    ),
    OkLink(
        chainId = "195",
        utilityAssetId = "a61b1842-30c0-431d-9bbc-fc3370562455"
    ),
    Reef(
        chainId = "7834781d38e4798d548e34ec947d19deea29df148a7bf32484b7b24dacf8d4b7",
        utilityAssetId = "55697eb0-ca77-47e3-a436-b05460ab1ead"
    ),
    Sora(
        chainId = "3266816be9fa51b32cfea58d3e33ca77246bc9618595a4300e44c8856a8d8a17",
        utilityAssetId = "b5a44630-920e-43ee-809f-61890d0888b0"
    ),
    SubSquid(
        chainId = "91b171bb158e2d3848fa23a9f1c25182fb8e20313b2c1eb49219da7a70ce90c3",
        utilityAssetId = "887a17c7-1370-4de0-97dd-5422e294fa75"
    ),
    SubQuery(
        chainId = "cd4d732201ebe5d6b014edda071c4203e16867305332301dc8d092044b28e554",
        utilityAssetId = "d0f2e718-99ee-4bc2-8dc2-1ce07f21c5ac"
    ),
    Zeta(
        chainId = "7001",
        utilityAssetId = "0f07791b-b091-49c1-81b7-9facba2b7db3"
    )

}


enum class ChainInfoConstants(val chainInfo: ChainInfo) {

    EtherScan(
        chainInfo = ChainInfo.WithEthereumType("1", "", ""),
    ),
    GiantSquid(
        chainInfo = ChainInfo.Simple("48239ef607d7928874027a43a67689209727dfb3d3dc5e5b03a39bdc2eda771a")
    ),
    OkLink(
        chainInfo = ChainInfo.WithAssetSymbol("195", "okb")
    ),
    Reef(
        chainInfo = ChainInfo.Simple("7834781d38e4798d548e34ec947d19deea29df148a7bf32484b7b24dacf8d4b7")
    ),
    Sora(
        chainInfo = ChainInfo.Simple("3266816be9fa51b32cfea58d3e33ca77246bc9618595a4300e44c8856a8d8a17")
    ),
    SubSquid(
        chainInfo = ChainInfo.Simple("91b171bb158e2d3848fa23a9f1c25182fb8e20313b2c1eb49219da7a70ce90c3")
    ),
    SubQuery(
        chainInfo = ChainInfo.Simple("cd4d732201ebe5d6b014edda071c4203e16867305332301dc8d092044b28e554")
    ),
    Zeta(
        chainInfo = ChainInfo.WithEthereumType("7001", "", "")
    )

}