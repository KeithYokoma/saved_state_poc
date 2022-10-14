// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.0-beta01" apply false
    id("com.android.library") version "7.4.0-beta01" apply false
    id("org.jetbrains.kotlin.android") version libs.versions.kotlin.get() apply false
    id("com.google.dagger.hilt.android") version libs.versions.hilt.get() apply false
    id("org.jlleitschuh.gradle.ktlint") version libs.versions.ktlintGradle.get()
}