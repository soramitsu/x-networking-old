package jp.co.soramitsu.xnetworking.encrypt.model

import jp.co.soramitsu.xnetworking.encrypt.MultiChainEncryption
import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair

class ImportAccountData(
    val keypair: Keypair,
    val multiChainEncryption: MultiChainEncryption,
    val username: String?,
    val networkTypeIdentifier: NetworkTypeIdentifier,
    val seed: ByteArray? = null
)

class ImportAccountMeta(
    val name: String?,
    val networkTypeIdentifier: NetworkTypeIdentifier,
    val encryption: MultiChainEncryption
)

sealed class NetworkTypeIdentifier {
    class Genesis(val genesis: String) : NetworkTypeIdentifier()

    class AddressByte(val addressByte: Short) : NetworkTypeIdentifier()

    object Undefined : NetworkTypeIdentifier()
}
