package com.chenxuan.gradle

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.chenxuan.gradle.ClassUtils.checkClassName
import java.io.IOException

class CustomTransform : Transform() {
    @Throws(TransformException::class, InterruptedException::class, IOException::class)
    override fun transform(transformInvocation: TransformInvocation) {
        val asmHelper = CustomAsmHelper()
        val baseTransform = BaseTransform(transformInvocation, object : TransformCallBack {
            override fun process(className: String, classBytes: ByteArray?): ByteArray? {
                if (checkClassName(className)) {
                    try {
                        return asmHelper.modifyClass(classBytes)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                return null
            }
        }, false)
        baseTransform.startTransform()
    }

    override fun getName(): String {
        return "CustomTransform"
    }

    override fun getInputTypes(): Set<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_JARS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope>? {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean {
        return true
    }
}