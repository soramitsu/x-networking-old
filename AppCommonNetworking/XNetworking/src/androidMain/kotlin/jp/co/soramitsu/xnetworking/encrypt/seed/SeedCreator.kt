package jp.co.soramitsu.xnetworking.encrypt.seed

import org.spongycastle.crypto.digests.SHA512Digest
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator
import org.spongycastle.crypto.params.KeyParameter
import java.text.Normalizer
import java.text.Normalizer.normalize

actual class SeedCreator {

    companion object {
        private const val SEED_PREFIX = "mnemonic"
        private const val FULL_SEED_LENGTH = 64
    }

    actual fun deriveSeed(
        entropy: ByteArray,
        passphrase: String?
    ): ByteArray {
        val generator = PKCS5S2ParametersGenerator(SHA512Digest())
        generator.init(
            entropy,
            normalize("$SEED_PREFIX${passphrase.orEmpty()}", Normalizer.Form.NFKD).toByteArray(),
            2048
        )
        val key = generator.generateDerivedMacParameters(FULL_SEED_LENGTH * 8) as KeyParameter
        return key.key.copyOfRange(0, FULL_SEED_LENGTH)
    }
}
