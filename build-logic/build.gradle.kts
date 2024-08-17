plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.bundles.plugins)
}

gradlePlugin {
    plugins {
        register("kmp") {
            id = "buildlogic.kmp"
            implementationClass = "io.github.kabos.buildlogic.KmpPlugin"
        }
        register("android") {
            id = "buildlogic.kmp.android"
            implementationClass = "io.github.kabos.buildlogic.KmpAndroidPlugin"
        }
        register("wasm") {
            id = "buildlogic.kmp.wasm"
            implementationClass = "io.github.kabos.buildlogic.KmpWasmPlugin"
        }
    }
}
