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
        register("test") {
            id = "test.hello"
            implementationClass = "io.github.kabos.buildlogic.HelloPlugin"
        }
        register("android") {
            id = "buildlogic.android"
            implementationClass = "io.github.kabos.buildlogic.AndroidPlugin"
        }
    }
}
