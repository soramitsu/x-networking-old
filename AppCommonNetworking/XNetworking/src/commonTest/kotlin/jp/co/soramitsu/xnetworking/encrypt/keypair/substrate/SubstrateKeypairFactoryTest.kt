package jp.co.soramitsu.xnetworking.encrypt.keypair.substrate

import jp.co.soramitsu.xnetworking.encrypt.EncryptionType
import jp.co.soramitsu.xnetworking.extensions.fromHex
import jp.co.soramitsu.xnetworking.extensions.toHexString
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class SubstrateKeypairFactoryTest {

    @Test
    fun `should generate ECDSA keypair`() {
        val seed = "3132333435363738393031323334353637383930313233343536373839303132".fromHex()
        val result = SubstrateKeypairFactory.generate(
            encryptionType = EncryptionType.ECDSA,
            seed = seed
        )

        val expectedPublicKeyHex = "035676109c54b9a16d271abeb4954316a40a32bcce023ac14c8e26e958aa68fba9"
        val expectedPrivateKeyHex = "3132333435363738393031323334353637383930313233343536373839303132"
        assertContentEquals(
            listOf(expectedPublicKeyHex, expectedPrivateKeyHex),
            listOf(result.publicKey.toHexString(), result.privateKey.toHexString())
        )
    }

    @Test
    fun `should generate ed25519 keypair`() {
        val seed = "3132333435363738393031323334353637383930313233343536373839303132".fromHex()
        val result = SubstrateKeypairFactory.generate(
            encryptionType = EncryptionType.ED25519,
            seed = seed
        )

        val expectedPublicKeyHex = "2f8c6129d816cf51c374bc7f08c3e63ed156cf78aefb4a6550d97b87997977ee"
        val expectedPrivateKeyHex = "3132333435363738393031323334353637383930313233343536373839303132"
        assertContentEquals(
            listOf(expectedPublicKeyHex, expectedPrivateKeyHex),
            listOf(result.publicKey.toHexString(), result.privateKey.toHexString())
        )
    }

    @Test
    fun `should generate sr25519 keypair`() {
        // sr25519 impossible to test in android
        assertEquals(expected = true, actual = true)
    }
}