// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.dagger.hilt.android") version "2.49" apply false
}
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.pluginVersion)
        classpath(libs.kotlin)
        classpath(libs.kotlinSerialization)
//        classpath(libs.mavenPublishPlugin)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}


