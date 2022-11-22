package jp.co.soramitsu.xnetworking.encrypt.keypair.ethereum

import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair

class Bip32ExtendedKeyPair(
    override val privateKey: ByteArray,
    override val publicKey: ByteArray,
    val chaincode: ByteArray
) : Keypair
