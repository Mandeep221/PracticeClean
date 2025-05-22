// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // hilt plugins
    alias(libs.plugins.hilt.android.gradle.plugin) apply false
    alias(libs.plugins.ksp.devtools) apply false
    alias(libs.plugins.android.library) apply false // required for ksp support
}