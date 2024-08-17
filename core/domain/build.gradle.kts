plugins {
    id("buildlogic.kmp")
    id("buildlogic.kmp.android")
    id("buildlogic.kmp.wasm")
}

android.namespace = "io.github.kabos.core.domain"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlin.result)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.junit)
        }
    }
}
