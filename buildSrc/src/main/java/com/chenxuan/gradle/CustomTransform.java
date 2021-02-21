package com.chenxuan.gradle;

import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.gradle.internal.pipeline.TransformManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Set;

public class CustomTransform extends Transform {

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        final CustomAsmHelper asmHelper = new CustomAsmHelper();
        BaseTransform baseTransform = new BaseTransform(transformInvocation, new TransformCallBack() {

            @Override
            public byte[] process(@NotNull String className, byte[] bytes) {
                if (ClassUtils.checkClassName(className)) {
                    try {
                        return asmHelper.modifyClass(bytes);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }, false);
        baseTransform.startTransform();
    }

    @Override
    public String getName() {
        return "CustomTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_JARS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return true;
    }
}
