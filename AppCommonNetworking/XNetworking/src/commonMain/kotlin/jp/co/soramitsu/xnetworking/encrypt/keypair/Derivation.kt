package jp.co.soramitsu.xnetworking.encrypt.keypair

import jp.co.soramitsu.xnetworking.encrypt.junction.Junction

internal fun <K : Keypair> KeypairFactory<K>.generate(
    seed: ByteArray,
    junctions: List<Junction>
): K {
    val parentKeypair = deriveFromSeed(seed)

    return junctions.fold(parentKeypair) { currentKeyPair, junction ->
        deriveChild(currentKeyPair, junction)
    }
}
