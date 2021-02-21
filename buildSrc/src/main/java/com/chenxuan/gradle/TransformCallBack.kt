package com.chenxuan.gradle

interface TransformCallBack {
    fun process(className: String, classBytes: ByteArray?): ByteArray?
}