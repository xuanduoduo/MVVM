package com.chenxuan.common.base

import kotlinx.coroutines.CoroutineScope

/**
 * @author cx
 */
internal typealias Block = suspend CoroutineScope.() -> Unit

internal typealias NetBlock<T> = suspend CoroutineScope.() -> BaseResponse<T>

internal typealias Success<T> = (BaseResponse<T>) -> Unit

internal typealias Error = (Exception) -> Unit