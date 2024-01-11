// Top-level build file where you can add configuration options common to all sub-projects/modules.


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



/**
 * Decides if this version is stable or not.
 */
fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return !isStable
}