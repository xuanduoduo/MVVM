package com.chenxuan.net

/**
 * @author cx
 */
class ApiException(val code: Int, val msg: String) : RuntimeException()