package com.chenxuan.common.base

/**
 * @author cx
 */
class BaseResponse<T> {
    var errorCode: Int = 0
    var errorMsg: String = ""
    var data: T? = null
}