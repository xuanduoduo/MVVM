package com.chenxuan.gradle

import java.io.File
import java.io.FileOutputStream

object ClassUtils {
    fun path2Classname(entryName: String): String {
        return entryName.replace(File.separator, ".").replace(".class", "")
    }

    fun checkClassName(className: String): Boolean {
        return (!className.contains("$")
                && !className.contains("BuildConfig")
                && !className.contains("_Impl")
                && !className.endsWith("R")
                && className.contains("chenxuan"))
    }

    fun saveFile(mTempDir: File?, modifiedClassBytes: ByteArray?): File? {
        var modified: File? = null
        try {
            if (modifiedClassBytes != null) {
                modified = mTempDir
                if (modified!!.exists()) {
                    modified.delete()
                }
                modified.createNewFile()
                val stream = FileOutputStream(modified)
                stream.write(modifiedClassBytes)
                stream.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return modified
    }
}