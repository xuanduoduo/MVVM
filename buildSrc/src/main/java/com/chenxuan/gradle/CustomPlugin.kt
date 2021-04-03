package com.chenxuan.gradle;

import com.android.build.gradle.AppPlugin;
import com.android.build.gradle.BaseExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CustomPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        boolean isApp = project.getPlugins().hasPlugin(AppPlugin.class);
        if (isApp) {
            project.getExtensions().findByType(BaseExtension.class)
                    .registerTransform(new CustomTransform());
        }
    }
}
