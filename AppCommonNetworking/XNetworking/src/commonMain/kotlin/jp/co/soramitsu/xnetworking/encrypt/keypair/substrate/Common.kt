package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.junction.Junction
import jp.co.soramitsu.xnetworking.encrypt.junction.JunctionType
import jp.co.soramitsu.xnetworking.encrypt.keypair.Keypair
import jp.co.soramitsu.xnetworking.encrypt.keypair.KeypairFactory
import jp.co.soramitsu.xnetworking.hash.blake2b256
import jp.co.soramitsu.xnetworking.scale.dataType.stringScale

class KeypairWithSeed(
    val seed: ByteArray,
    override val privateKey: ByteArray,
    override val publicKey: ByteArray
) : Keypair

abstract class OtherSubstrateKeypairFactory: KeypairFactory<KeypairWithSeed> {

    abstract val hardDerivationPrefix: String

    override fun deriveChild(parent: KeypairWithSeed, junction: Junction): KeypairWithSeed {
        if (junction.type == JunctionType.HARD) {
            val prefix = stringScale.encode(hardDerivationPrefix)
            val newSeed = (prefix + parent.seed + junction.chaincode).blake2b256()
            return deriveFromSeed(newSeed)
        } else {
            throw KeypairFactory.SoftDerivationNotSupported()
        }
    }
}