package com.chenxuan.login.api

import com.chenxuan.bean.login.Chapters
import com.chenxuan.common.base.BaseService
import com.chenxuan.common.base.BaseResponse
import retrofit2.http.GET

/**
 * @author cx
 */
interface LoginApiService : BaseService {
    @GET("/wxarticle/chapters/json")
    suspend fun getChapters(): BaseResponse<List<Chapters>>
}