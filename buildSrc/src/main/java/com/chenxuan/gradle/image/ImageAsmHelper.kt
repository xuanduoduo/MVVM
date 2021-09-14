package com.chenxuan.gradle.image

import com.chenxuan.gradle.AsmHelper
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

class ImageAsmHelper : AsmHelper {

    override fun modifyClass(srcClass: ByteArray): ByteArray {
        val classReader = ClassReader(srcClass)
        val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
        val cv: ClassVisitor = ImageClassVisitor(classWriter)
        classReader.accept(cv, ClassReader.EXPAND_FRAMES)
        return classWriter.toByteArray()
    }
}