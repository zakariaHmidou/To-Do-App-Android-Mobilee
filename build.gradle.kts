@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt.dagger) apply false
}

buildscript  {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("serialization", "1.7.20"))
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}


