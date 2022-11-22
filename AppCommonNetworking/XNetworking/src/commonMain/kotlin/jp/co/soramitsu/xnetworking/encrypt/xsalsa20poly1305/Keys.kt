package jp.co.soramitsu.xnetworking.encrypt.xsalsa20poly1305

expect fun generateSecretKey(): ByteArray

expect fun generatePrivateKey(): ByteArray

expect fun generatePublicKey(privateKey: ByteArray?): ByteArray

expect fun sharedSecret(
    publicKey: ByteArray?,
    privateKey: ByteArray?
): ByteArray