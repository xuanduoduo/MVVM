package com.chenxuan.gradle

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val isApp = project.plugins.hasPlugin(AppPlugin::class.java)
        if (isApp) {
            project.extensions.findByType(BaseExtension::class.java)?.registerTransform(CustomTransform())
        }
    }
}