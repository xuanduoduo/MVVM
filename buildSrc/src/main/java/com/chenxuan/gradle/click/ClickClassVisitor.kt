package com.chenxuan.gradle.click

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ClickClassVisitor(classVisitor: ClassVisitor) : ClassVisitor(Opcodes.ASM5, classVisitor) {
    private val className = "android/view/View\$OnClickListener"

    private var needVisitClass = false

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        needVisitClass = needVisitClass(interfaces)
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var mv = cv.visitMethod(access, name, descriptor, signature, exceptions)
        if (needVisitClass && needVisitMethod(name, descriptor)) {
            mv = ClickMethodVisitor(mv, access, name, descriptor)
        }
        return mv
    }

    private fun needVisitClass(interfaces: Array<out String>?): Boolean {
        interfaces ?: return false
        return interfaces.contains(className)
    }

    private fun needVisitMethod(name: String?, descriptor: String?): Boolean {
        return name == "onClick" && descriptor == "(Landroid/view/View;)V"
    }
}