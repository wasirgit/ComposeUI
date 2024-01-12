plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version libs.versions.ksp
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

android {
    namespace = "com.example.JetPackUI"
    compileSdk = libs.versions.compileSdk.get().toIntOrNull()

    defaultConfig {
        applicationId = "com.example.JetPackUI"
        minSdk = libs.versions.minSdk.get().toIntOrNull()
        targetSdk = libs.versions.targetSdk.get().toIntOrNull()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
    kotlin {
        jvmToolchain(11)
    }
}

dependencies {
    implementation(libs.androidMaterial)
    implementation(libs.compose.ui)
    implementation(libs.compose.viewModel)
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)

    ksp(libs.ksp)
    implementation(libs.androidx.lifecycleRuntimeKtx)
    implementation(libs.androidx.activityCompose)
    debugImplementation(libs.compose.preview)
    debugImplementation(libs.androidx.ui.tooling)
}