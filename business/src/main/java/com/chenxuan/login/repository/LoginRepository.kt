package com.chenxuan.login.repository

import com.chenxuan.bean.login.Chapters
import com.chenxuan.common.base.BaseRepository
import com.chenxuan.login.api.LoginApiService
import com.chenxuan.net.ApiServiceUtil
import com.chenxuan.net.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author cx
 */
class LoginRepository : BaseRepository() {
    private val service = ApiServiceUtil.getApiService<LoginApiService>()

    suspend fun getChapters(): BaseResponse<List<Chapters>> {
        return withContext(Dispatchers.IO) {
            service.getChapters()
        }
    }
}