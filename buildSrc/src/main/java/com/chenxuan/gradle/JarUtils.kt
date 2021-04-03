package com.chenxuan.gradle

import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.compress.utils.IOUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

internal object JarUtils {
    @Throws(IOException::class)
    fun modifyJarFile(
        jarFile: File, tempDir: File?,
        transform: BaseTransform
    ): File {
        val hexName = DigestUtils.md5Hex(jarFile.absolutePath).substring(0, 8)
        val optJar = File(tempDir, hexName + jarFile.name)
        val jarOutputStream = JarOutputStream(FileOutputStream(optJar))
        val file = JarFile(jarFile)
        val enumeration = file.entries()
        while (enumeration.hasMoreElements()) {
            val jarEntry = enumeration.nextElement()
            val inputStream = file.getInputStream(jarEntry)
            val entryName = jarEntry.name
            val zipEntry = ZipEntry(entryName)
            jarOutputStream.putNextEntry(zipEntry)
            var modifiedClassBytes: ByteArray? = null
            val sourceClassBytes = IOUtils.toByteArray(inputStream)
            if (entryName.endsWith(".class")) {
                try {
                    modifiedClassBytes = transform.process(entryName, sourceClassBytes)
                } catch (ignored: Exception) {
                }
            }
            if (modifiedClassBytes == null) {
                jarOutputStream.write(sourceClassBytes)
            } else {
                jarOutputStream.write(modifiedClassBytes)
            }
            jarOutputStream.closeEntry()
        }
        jarOutputStream.close()
        file.close()
        return optJar
    }

    @Throws(IOException::class)
    fun scanJarFile(jarFile: File?): HashSet<String> {
        val hashSet = HashSet<String>()
        val file = JarFile(jarFile)
        val enumeration = file.entries()
        while (enumeration.hasMoreElements()) {
            val jarEntry = enumeration.nextElement()
            val entryName = jarEntry.name
            if (entryName.endsWith(".class")) {
                hashSet.add(entryName)
            }
        }
        file.close()
        return hashSet
    }

    @Throws(IOException::class)
    fun deleteJarScan(jarFile: File?, removeClasses: List<String?>, callBack: DeleteCallBack?) {
        val file = JarFile(jarFile)
        val enumeration = file.entries()
        while (enumeration.hasMoreElements()) {
            val jarEntry = enumeration.nextElement()
            val entryName = jarEntry.name
            if (entryName.endsWith(".class") && removeClasses.contains(entryName)) {
                val inputStream = file.getInputStream(jarEntry)
                val sourceClassBytes = IOUtils.toByteArray(inputStream)
                try {
                    callBack?.delete(entryName, sourceClassBytes)
                } catch (ignored: Exception) {
                }
            }
        }
        file.close()
    }

    @Throws(IOException::class)
    fun deleteJarScan(jarFile: File?, callBack: DeleteCallBack?) {
        val file = JarFile(jarFile)
        val enumeration = file.entries()
        while (enumeration.hasMoreElements()) {
            val jarEntry = enumeration.nextElement()
            val inputStream = file.getInputStream(jarEntry)
            val entryName = jarEntry.name
            val sourceClassBytes = IOUtils.toByteArray(inputStream)
            if (entryName.endsWith(".class")) {
                try {
                    callBack?.delete(entryName, sourceClassBytes)
                } catch (ignored: Exception) {
                }
            }
        }
        file.close()
    }
}