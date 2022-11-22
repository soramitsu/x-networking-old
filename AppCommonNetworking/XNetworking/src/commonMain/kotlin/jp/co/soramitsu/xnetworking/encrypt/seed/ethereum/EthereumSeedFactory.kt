package jp.co.soramitsu.xnetworking.encrypt.seed.ethereum

/*
import jp.co.soramitsu.xnetworking.encrypt.mnemonic.MnemonicCreator
import jp.co.soramitsu.xnetworking.encrypt.mnemonic.Mnemonic
import jp.co.soramitsu.xnetworking.encrypt.seed.SeedCreator
import jp.co.soramitsu.xnetworking.encrypt.seed.SeedFactory

object EthereumSeedFactory : SeedFactory {

    override fun createSeed(length: Mnemonic.Length, password: String?): SeedFactory.Result {
        val mnemonic = MnemonicCreator.randomMnemonic(length)
        val seed = SeedCreator.deriveSeed(mnemonic.words.encodeToByteArray(), password)

        return SeedFactory.Result(seed, mnemonic)
    }

    override fun deriveSeed(mnemonicWords: String, password: String?): SeedFactory.Result {
        val mnemonic = MnemonicCreator.fromWords(mnemonicWords)
        val seed = SeedCreator.deriveSeed(mnemonic.words.encodeToByteArray(), password)

        return SeedFactory.Result(seed, mnemonic)
    }
}
*/
// TODO: infinity_coder