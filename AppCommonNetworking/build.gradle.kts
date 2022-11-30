buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://nexus.iroha.tech/repository/maven-soramitsu/")
    }

    val kotlinVersion = project.properties["kotlin_version"].toString()
    val agpVersion = project.properties["agp_version"].toString()
    val sqlDelightVersion = project.properties["sqldelight_version"].toString()
    val rustGradleVersion = project.properties["rust_gradle_version"].toString()

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath("com.android.tools.build:gradle:$agpVersion")
        classpath("org.mozilla.rust-android-gradle:plugin:$rustGradleVersion")
    }
}

allprojects {
    repositories {
        google()
        maven(url = "https://nexus.iroha.tech/repository/maven-soramitsu/")
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}