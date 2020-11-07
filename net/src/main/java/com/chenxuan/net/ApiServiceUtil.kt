package com.chenxuan.net

/**
 * @author cx
 */
object ApiServiceUtil {
    private val map = mutableMapOf<Class<*>, Any?>()

    inline fun <reified T> getApiService() =
        getService(T::class.java)

    @Suppress("UNCHECKED_CAST")
    fun <T> getService(type: Class<T>): T {
        return if (map.containsKey(type)) {
            map[type] as T
        } else {
            val temp = Api.retrofit.create(type)
            map[type] = temp
            temp
        }
    }
}