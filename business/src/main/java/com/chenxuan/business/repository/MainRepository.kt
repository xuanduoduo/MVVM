package com.chenxuan.business.repository

import com.chenxuan.bean.business.Chapters
import com.chenxuan.common.base.BaseRepository
import com.chenxuan.business.api.MainApiService
import com.chenxuan.net.ApiServiceUtil
import com.chenxuan.net.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author cx
 */
class MainRepository : BaseRepository() {
    private val service = ApiServiceUtil.getApiService<MainApiService>()

    suspend fun getChapters(): BaseResponse<List<Chapters>> {
        return withContext(Dispatchers.IO) {
            service.getChapters()
        }
    }
}