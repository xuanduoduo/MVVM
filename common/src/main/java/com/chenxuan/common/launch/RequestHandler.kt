package com.chenxuan.common.launch

import com.chenxuan.common.base.ErrorBlock
import com.chenxuan.common.base.SuccessBlock

/**
 * @author cx
 */
class RequestHandler<T> {
    var successListener: SuccessBlock<T>? = null
    var errorListener: ErrorBlock? = null
}

infix fun <T> RequestHandler<T>.success(block: SuccessBlock<T>) = this.apply {
    successListener = block
}

infix fun <T> RequestHandler<T>.error(block: ErrorBlock) = this.apply {
    errorListener = block
}