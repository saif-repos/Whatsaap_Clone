//// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.kotlin.android) apply false
//    alias(libs.plugins.ksp) // ✅ use alias
//
//    id("com.google.dagger.hilt.android") version "2.56.2" apply false
//    alias(libs.plugins.google.gms.google.services) apply false
////
////    id("com.google.devtools.ksp") version "1.9.23-1.0.20" // ✅ match with Kotlin 1.9.23
////    id("com.google.dagger.hilt.android")
////    id("kotlin-kapt")
//}
//
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}