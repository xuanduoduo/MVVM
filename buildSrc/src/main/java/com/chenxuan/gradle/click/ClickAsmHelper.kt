package com.chenxuan.gradle.click

import com.chenxuan.gradle.AsmHelper
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

class ClickAsmHelper : AsmHelper {

    override fun modifyClass(srcClass: ByteArray): ByteArray {
        val classReader = ClassReader(srcClass)
        val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
        val cv: ClassVisitor = ClickClassVisitor(classWriter)
        classReader.accept(cv, ClassReader.EXPAND_FRAMES)
        return classWriter.toByteArray()
    }
}