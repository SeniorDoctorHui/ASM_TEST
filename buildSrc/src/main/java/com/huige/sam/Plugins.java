package com.huige.sam;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class Plugins implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        System.out.println("------------Plugins start --------------");

        AppExtension appExtension = target.getExtensions().findByType(AppExtension.class);
        appExtension.registerTransform(new TransformTest());

    }
}
