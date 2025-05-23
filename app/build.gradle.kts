plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android.gradle.plugin)
    alias(libs.plugins.ksp.devtools)
}

android {
    namespace = "com.example.practice"
    compileSdk = 35

    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.1" // 👈 REQUIRED for Kotlin 2.0.21
    }

    defaultConfig {
        applicationId = "com.example.practice"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    // Compose lifecycle integration
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Compose integration with Activity
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui.tooling.preview)

    // feature modules
    implementation(project(":profile"))
    implementation(project(":dashboard"))

    // compose navigation
     implementation(libs.androidx.navigation.compose)

    // coroutines
    implementation(libs.coroutines)

    // hilt
    implementation(libs.hilt)
    ksp(libs.hilt.ksp)

    // implementation(libs.androidx.activity.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}