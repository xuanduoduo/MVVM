package com.chenxuan.net

import com.blankj.utilcode.util.PathUtils
import com.orhanobut.logger.Logger
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import java.io.File
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * @author cx
 */
object Api {
    private const val HOST = "https://www.wanandroid.com"

    lateinit var retrofit: Retrofit

    fun init() {
        initRetrofit(initOkHttpClient())
    }

    /**
     * 初始化Retrofit
     */
    private fun initRetrofit(okHttpClient: OkHttpClient) {
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(HOST)
            .addConverterFactory(CxConverterFactory.create())
            .build()
    }

    /**
     * 初始化OkHttp
     */
    private fun initOkHttpClient(): OkHttpClient {
        val cache = Cache(File(PathUtils.getExternalAppCachePath(), "HttpCache"), 1024 * 1024 * 80)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .cache(cache)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    /**
     * 打印JSON的拦截器
     */
    private var loggingInterceptor: Interceptor = invoke {
        val request = it.request()
        val requestBuffer = Buffer()
        if (request.body != null) {
            request.body!!.writeTo(requestBuffer)
        } else {
            Logger.d("request.body() == null")
        }
        val response = it.proceed(request)
        val responseBody = response.body
        val source = responseBody!!.source()
        source.request(Long.MAX_VALUE) // Buffer the entire body.
        val buffer = source.buffer
        Logger.json(buffer.clone().readString(Charset.forName("UTF-8")))
        response
    }
}