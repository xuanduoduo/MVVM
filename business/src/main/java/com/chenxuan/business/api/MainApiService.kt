package com.chenxuan.business.api

import com.chenxuan.bean.business.Chapters
import com.chenxuan.common.base.BaseService
import com.chenxuan.net.BaseResponse
import retrofit2.http.GET

/**
 * @author cx
 */
interface MainApiService : BaseService {
    @GET("/wxarticle/chapters/json")
    suspend fun getChapters(): BaseResponse<List<Chapters>>
}