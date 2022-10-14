plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = "com.github.keithyokoma.poc.fatbundle"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.github.keithyokoma.poc.fatbundle"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.jetpackComposeCompiler.get()
    }
}

dependencies {
    implementation(libs.bundles.kotlinAndroid)
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.jetpackCompose)
    implementation(libs.bundles.networking)
    implementation(libs.bundles.daggerHilt)
    kapt(libs.bundles.daggerHiltKapt)
    implementation(libs.timber)
    implementation(libs.remoteData)
    implementation(libs.intentLogger)
    implementation(libs.tooLargeTool)
}