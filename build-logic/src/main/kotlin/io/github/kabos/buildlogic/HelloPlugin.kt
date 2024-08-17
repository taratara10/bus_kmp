package io.github.kabos.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("hello --ss")
    }
}
