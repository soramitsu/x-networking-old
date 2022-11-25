package jp.co.soramitsu.xnetworking.encrypt

object Sr25519 {
    /// Size of input SEED for derivation, bytes
    const val SEED_SIZE = 32

    /// Size of CHAINCODE, bytes
    const val CHAINCODE_SIZE = 32

    /// Size of SR25519 PUBLIC KEY, bytes
    const val PUBLIC_SIZE = 32

    /// Size of SR25519 PRIVATE (SECRET) KEY, which consists of [32 bytes key | 32 bytes nonce]
    const val SECRET_SIZE = 64

    /// Size of SR25519 SIGNATURE, bytes
    const val SIGNATURE_SIZE = 64

    /// Size of SR25519 KEYPAIR. [32 bytes key | 32 bytes nonce | 32 bytes public]
    const val KEYPAIR_SIZE = 96

    init {
        System.loadLibrary("sr25519java")
    }

    @JvmStatic
    external fun test(hello_what: String?): String?

    /**
     * Verify a message and its corresponding against a public key;
     *
     * @param signature:  verify this signature
     * @param message:    arbitrary message
     * @param public_key: verify with this public key
     * @return true if signature is valid, false otherwise
     */
    @JvmStatic
    external fun verify(signature: ByteArray, message: ByteArray, public_key: ByteArray): Boolean

    /**
     * Sign a message
     * The combination of both public and private key must be provided.
     * This is effectively equivalent to a keypair.
     *
     * @param public_key: public key
     * @param secret:     private key (secret)
     * @param message:    Arbitrary message
     * @return the signature
     */
    @JvmStatic
    external fun sign(public_key: ByteArray, secret: ByteArray, message: ByteArray): ByteArray

    /**
     * Perform a derivation on a secret
     *
     * @param pair: existing keypair - input buffer of   KEYPAIR_SIZE bytes
     * @param cc:   chaincode - input buffer of   CHAINCODE_SIZE bytes
     * @return pre-allocated output buffer of   KEYPAIR_SIZE bytes
     */
    @JvmStatic
    external fun deriveKeypairHard(pair: ByteArray, cc: ByteArray): ByteArray

    /**
     * Perform a derivation on a secret
     *
     * @param pair: existing keypair - input buffer of   KEYPAIR_SIZE bytes
     * @param cc:   chaincode - input buffer of   CHAINCODE_SIZE bytes
     * @return keypair: pre-allocated output buffer of   KEYPAIR_SIZE bytes
     */
    @JvmStatic
    external fun deriveKeypairSoft(pair: ByteArray, cc: ByteArray): ByteArray

    /**
     * Perform a derivation on a publicKey
     *
     * @param public_key: public key - input buffer of   PUBLIC_SIZE bytes
     * @param cc:         chaincode - input buffer of   CHAINCODE_SIZE bytes
     * @return pre-allocated output buffer of   PUBLIC_SIZE bytes
     */
    @JvmStatic
    external fun derivePublicSoft(public_key: ByteArray, cc: ByteArray): ByteArray

    /**
     * Generate a key pair.
     *
     * @param seed: generation seed - input buffer of   SEED_SIZE bytes
     * @return keypair [32b key | 32b nonce | 32b public], pre-allocated output buffer of   KEYPAIR_SIZE bytes
     */
    @JvmStatic
    external fun keypairFromSeed(seed: ByteArray): ByteArray

    /**
     * Converts a secret key, provided as an array of 64 bytes,
     * to a corresponding ed25519 expanded secret key
     * @return an array of 64 bytes, with the first 32 bytes being the secret scalar shifted ed25519 style,
     * and the last 32 bytes being the seed for nonces
     */
    @JvmStatic
    external fun toEd25519Bytes(secret: ByteArray): ByteArray

    /**
     * Converts an ed25519 expanded secret key to a corresponding sr25519 secret key.
     * @return an array of 64 bytes, with the first 32 bytes being the secret scalar
     * represented canonically, and the last 32 bytes being the seed for nonces
     */
    @JvmStatic
    external fun fromEd25519Bytes(ed_expanded_secret: ByteArray): ByteArray
}