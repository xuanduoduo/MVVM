package com.chenxuan.common.utils.util

import com.google.gson.Gson

/**
 * @author cx
 */
val gson by lazy {
    Gson()
}

inline fun <reified T> String.toBean(): T? {
    return gson.fromJson(this, T::class.java)
}

fun <T> T.toJsonString(): String {
    return gson.toJson(this)
}