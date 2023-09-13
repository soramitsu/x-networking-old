@Library('jenkins-library') _

new org.soramitsu.mainLibrary().call(
  agentLabel: "android",
  skipSonar: true,
  skipDojo: true,
  agentImage: "android-build-box-jdk11:latest",
  nexusCredentials: "bot-soramitsu-rw",
  buildCommand: './gradlew clean build',
  testCommand: './gradlew test --info',
  publishCommand:
    '''./gradlew :core:basic:publishAndroidReleasePublicationToScnRepoRepository &&
    ./gradlew :core:fearlesswallet:publishAndroidReleasePublicationToScnRepoRepository &&
    ./gradlew :core:sorawallet:publishAndroidReleasePublicationToScnRepoRepository''',
  publishLibrary: true,
  skipDockerImage: true,
  dojoProductType: "x-networking"
)