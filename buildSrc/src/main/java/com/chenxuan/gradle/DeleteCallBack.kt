package com.chenxuan.gradle

interface DeleteCallBack {
    fun delete(className: String, classBytes: ByteArray)
}