@Library('jenkins-library') _

new org.android.ShareFeature().call(
  detekt: false,
  test: true,
  dockerImage: "build-tools/android-build-box:jdk17",
  nexusCredentials: "bot-soramitsu-rw",
  buildCmd: 'clean build',
  testCmd: 'test --info',
  publishCmd:
    ''':lib:basic:publishAndroidReleasePublicationToScnRepoRepository''',
  dojo: true,
  dojoProductType: "x-networking"
)