package com.chenxuan.common.utils.key

import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author cx
 */
class KeyV<T>(private val key: String, private val defaultValue: T) :
    ReadWriteProperty<Any, T> {

    private val keyValue by lazy {
        MMKV.defaultMMKV()
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return when (defaultValue) {
            is Int -> keyValue.decodeInt(key, defaultValue as Int)
            is Long -> keyValue.decodeLong(key, defaultValue as Long)
            is Float -> keyValue.decodeFloat(key, defaultValue as Float)
            is Double -> keyValue.decodeDouble(key, defaultValue as Double)
            is Boolean -> keyValue.decodeBool(key, defaultValue as Boolean)
            is String -> keyValue.decodeString(key, defaultValue as String)
            else -> {
                throw KeyVException("get wrong type")
            }
        } as T
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        keyValue.run {
            when (value) {
                is Int -> encode(key, value)
                is Long -> encode(key, value)
                is Float -> encode(key, value)
                is Double -> encode(key, value)
                is Boolean -> encode(key, value)
                is String -> encode(key, value)
                else -> {
                    throw KeyVException("set wrong type")
                }
            }
        }
    }
}