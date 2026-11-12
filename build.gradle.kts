plugins {
    alias(libs.plugins.android.application) apply false
    // Safe Args: To generate classes for passing my data between screens hehe
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
    id("com.google.devtools.ksp") version "2.3.10" apply false
}