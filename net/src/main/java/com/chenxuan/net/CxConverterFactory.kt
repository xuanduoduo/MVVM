package com.chenxuan.net

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @author cx
 */
class CxConverterFactory private constructor(private val gson: Gson) : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return CxResponseBodyConverter(gson, adapter as TypeAdapter<*>)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return CxRequestBodyConverter(gson, adapter as TypeAdapter<*>)
    }

    companion object {
        @JvmOverloads
        fun create(gson: Gson? = Gson()): CxConverterFactory {
            if (gson == null) throw NullPointerException("gson == null")
            return CxConverterFactory(gson)
        }
    }

}