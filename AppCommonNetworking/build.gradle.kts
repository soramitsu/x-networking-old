buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    val sqlDelightVersion: String by project

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.10")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath("com.android.tools.build:gradle:7.2.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}