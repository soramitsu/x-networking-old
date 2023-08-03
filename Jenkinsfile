@Library('jenkins-library') _

new org.soramitsu.mainLibrary().call(
  agentLabel: "android",
  skipSonar: true,
  skipDojo: true,
  agentImage: "android-build-box-jdk11:latest",
  nexusCredentials: "bot-soramitsu-rw",
  buildCommand: 'cd AppCommonNetworking && ./gradlew clean build',
  testCommand: 'cd AppCommonNetworking && ./gradlew test --info',
  publishCommand: 'cd AppCommonNetworking && ./gradlew :XNetworking:publishAndroidReleasePublicationToScnRepoRepository',
  publishLibrary: true,
  skipDockerImage: true,
  dojoProductType: "x-networking"
)