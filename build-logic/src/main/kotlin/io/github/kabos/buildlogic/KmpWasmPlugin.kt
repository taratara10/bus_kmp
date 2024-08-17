package io.github.kabos.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

@OptIn(ExperimentalWasmDsl::class)
@Suppress("unused")
class KmpWasmPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            kotlin {
                wasmJs {
                    browser {
                        commonWebpackConfig {
                            devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                                static = (static ?: mutableListOf()).apply {
                                    // Serve sources to debug inside browser
                                    add(project.projectDir.path)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
