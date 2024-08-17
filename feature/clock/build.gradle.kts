plugins {
    id("buildlogic.kmp")
    id("buildlogic.kmp.android")
    id("buildlogic.kmp.wasm")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

android.namespace = "io.github.kabos.feature.clock"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.core.model)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlin.result)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.lifecycle.viewmodel.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.junit)
        }
    }
}
