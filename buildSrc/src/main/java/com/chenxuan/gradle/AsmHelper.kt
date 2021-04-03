package com.chenxuan.gradle

import java.io.IOException

interface AsmHelper {
    @Throws(IOException::class)
    fun modifyClass(srcClass: ByteArray?): ByteArray
}