package com.chenxuan.gradle.image

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes

class ImageClassVisitor(classVisitor: ClassVisitor) : ClassVisitor(Opcodes.ASM5, classVisitor) {
    private val originImage = "android/widget/ImageView"
    private val monitorImage = "com/chenxuan/widget/view/MonitorImage"

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        var newSuperName = superName
        if (name != monitorImage && superName == originImage) {
            newSuperName = monitorImage
        }
        super.visit(version, access, name, signature, newSuperName, interfaces)
    }
}