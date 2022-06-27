package com.chenxuan.common.base

import com.chenxuan.common.launch.LaunchResult
import com.chenxuan.net.BaseResponse
import kotlinx.coroutines.CoroutineScope

/**
 * @author cx
 */
internal typealias Launch = suspend CoroutineScope.() -> Unit

internal typealias NetBlock<T> = suspend CoroutineScope.() -> BaseResponse<T>

internal typealias SuccessBlock<T> = (LaunchResult.Success<T>) -> Unit

internal typealias ErrorBlock = (LaunchResult.Error) -> Unit