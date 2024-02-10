@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.agp.application)
    alias(libs.plugins.kotlin.android)
    id(libs.plugins.kotlin.serialization.get().pluginId)
    id(libs.plugins.navigation.safeArgs.get().pluginId)
}

android {
    namespace = "com.example.onlinestore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.onlinestore"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.test.espresso)
    implementation(libs.bundles.ui)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.navigation)
    implementation(libs.koin.android)
    implementation(libs.coroutines.android)
    implementation(libs.serialization)
}