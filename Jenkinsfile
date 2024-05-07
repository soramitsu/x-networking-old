@Library('jenkins-library') _

def pipeline = new org.android.ShareFeature(
  steps: this,
  test: true,
  agentImage: "build-tools/android-build-box-jdk11:latest",
  buildCmd: 'clean build',
  testCmd: 'test --info',
  publishCmd: ':core:basic:publishAndroidReleasePublicationToScnRepoRepository :core:fearlesswallet:publishAndroidReleasePublicationToScnRepoRepository :core:sorawallet:publishAndroidReleasePublicationToScnRepoRepository',
  sonarProjectKey: "sora:x-networking",
  sonarProjectName: "x-networking",
  dojoProductType: "sora-mobile"
)

pipeline.runPipeline()