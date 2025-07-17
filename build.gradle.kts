// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("com.google.dagger.hilt.android") version "2.57" apply false

}

buildscript {
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
    }
}