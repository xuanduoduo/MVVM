package com.chenxuan.gradle

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import java.io.File
import java.io.FileWriter

class CustomAsmHelper : AsmHelper {

    private val classNodeMap = hashMapOf<String, ClassNode>()

    private val path = System.getProperty("user.home") + File.separator + "Downloads"
    private val targetFile = File(path + "/inject_" + System.currentTimeMillis() + ".txt")

    override fun modifyClass(srcClass: ByteArray?): ByteArray {
        val classNode = ClassNode(Opcodes.ASM5)
        val classReader = ClassReader(srcClass)
        //1 将读入的字节转为classNode
        classReader.accept(classNode, 0)
        classNodeMap[classNode.name] = classNode
        //2 操作
        printName(classNode)
        val classWriter = ClassWriter(0)
        //3  将classNode转为字节数组
        classNode.accept(classWriter)
        return classWriter.toByteArray()
    }

    private fun printName(classNode: ClassNode) {
        val fileWriter = FileWriter(targetFile, true)
        fileWriter.append("${classNode.name}\n")
        fileWriter.flush()
        fileWriter.close()
    }

}