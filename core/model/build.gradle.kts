plugins {
    id("buildlogic.kmp")
    id("buildlogic.kmp.android")
    id("buildlogic.kmp.wasm")
}

android.namespace = "io.github.kabos.core.model"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.junit)
        }
    }
}
