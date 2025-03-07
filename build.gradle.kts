// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

buildscript {
    dependencies {
        // Add Hilt Gradle plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}