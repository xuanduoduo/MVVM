package com.chenxuan.net

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

/**
 * @author cx
 */
internal class CxResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) :
    Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val response = value.string()
        val baseResponse = gson.fromJson(response, BaseResponse::class.java)
        if (baseResponse.errorCode != BaseResponseCode.SUCCESS) {
            value.close()
            throw ApiException(baseResponse.errorCode, baseResponse.errorMsg)
        }
        return value.use {
            adapter.fromJson(response)
        }
    }

}