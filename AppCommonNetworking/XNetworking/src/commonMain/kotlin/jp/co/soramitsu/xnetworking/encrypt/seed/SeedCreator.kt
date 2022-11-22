package jp.co.soramitsu.xnetworking.encrypt.seed

expect class SeedCreator() {

    fun deriveSeed(
        entropy: ByteArray,
        passphrase: String? = null
    ): ByteArray
}