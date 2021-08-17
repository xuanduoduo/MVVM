package com.chenxuan.gradle.click

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

object ClickMethodVisitor {

    operator fun invoke(
        mv: MethodVisitor,
        access: Int,
        name: String?,
        descriptor: String?,
    ): MethodVisitor {
        return object : AdviceAdapter(Opcodes.ASM5, mv, access, name, descriptor) {
            override fun onMethodEnter() {
                mv.visitLdcInsn("chenxuan----->")
                mv.visitLdcInsn("onMethodEnter----->$name")
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "android/util/Log",
                    "i",
                    "(Ljava/lang/String;Ljava/lang/String;)I",
                    false
                )
                mv.visitInsn(Opcodes.POP)
                super.onMethodEnter()
            }

            override fun onMethodExit(opcode: Int) {
                mv.visitLdcInsn("chenxuan----->")
                mv.visitLdcInsn("onMethodExit----->$name")
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "android/util/Log",
                    "i",
                    "(Ljava/lang/String;Ljava/lang/String;)I",
                    false
                )
                mv.visitInsn(Opcodes.POP)
                super.onMethodExit(opcode)
            }
        }
    }
}