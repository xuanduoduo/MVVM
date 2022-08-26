package com.chenxuan.common.launch

import com.chenxuan.net.BaseResponse

/**
 * @author cx
 */
sealed class LaunchResult {
    data class Success<T>(val response: BaseResponse<T>) : LaunchResult()
    data class Error(val error: Throwable) : LaunchResult()
}
