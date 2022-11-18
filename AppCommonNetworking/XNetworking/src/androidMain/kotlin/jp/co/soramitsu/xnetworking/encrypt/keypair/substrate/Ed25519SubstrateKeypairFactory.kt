package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.SecurityProviders
import jp.co.soramitsu.xnetworking.encrypt.keypair.BaseKeypair
import net.i2p.crypto.eddsa.EdDSAKey
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec
import java.security.KeyFactory

private const val ED25519_PRIVATE_KEY_PREFIX = "302e020100300506032b657004220420"
private const val ED25519_PUBLIC_KEY_PREFIX = "302a300506032b6570032100"

actual class Ed25519SubstrateKeypairFactory : BaseEd25519SubstrateKeypairFactory() {

    init {
        SecurityProviders.requireEdDSA
    }

    actual override fun generateBaseKeypair(seed: ByteArray): BaseKeypair {
        val keyFac = KeyFactory.getInstance(EdDSAKey.KEY_ALGORITHM, "EdDSA")
        val spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)
        val privKeySpec = EdDSAPrivateKeySpec(seed, spec)
        val private = keyFac.generatePrivate(privKeySpec).encoded
        val publicKeySpec = EdDSAPublicKeySpec(privKeySpec.a, spec)
        val public = keyFac.generatePublic(publicKeySpec).encoded

        return BaseKeypair(
            privateKey = private.copyOfRange(
                ED25519_PRIVATE_KEY_PREFIX.length / 2,
                private.size
            ),
            publicKey = public.copyOfRange(ED25519_PUBLIC_KEY_PREFIX.length / 2, public.size)
        )
    }
}