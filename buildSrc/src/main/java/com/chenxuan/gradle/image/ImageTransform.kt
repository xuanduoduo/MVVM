package com.chenxuan.gradle.image

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.chenxuan.gradle.BaseTransform
import com.chenxuan.gradle.ClassUtils
import com.chenxuan.gradle.TransformCallBack
import java.io.IOException

class ImageTransform : Transform() {
    @Throws(TransformException::class, InterruptedException::class, IOException::class)
    override fun transform(transformInvocation: TransformInvocation) {
        val asmHelper = ImageAsmHelper()
        val baseTransform = BaseTransform(transformInvocation, object : TransformCallBack {
            override fun process(className: String, classBytes: ByteArray?): ByteArray? {
                if (ClassUtils.checkClassName(className)) {
                    try {
                        classBytes ?: return null
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
        return "ImageTransform"
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