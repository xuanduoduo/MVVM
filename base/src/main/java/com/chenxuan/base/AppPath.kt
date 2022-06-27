package com.chenxuan.base

/**
 * @author cx
 */
class AppPath {
    companion object {
        private const val APPLICATION_COMMON = "com.chenxuan.net.NetApp"
        private const val APPLICATION_NET = "com.chenxuan.common.CommonApp"

        val appPaths = listOf(
            APPLICATION_COMMON,
            APPLICATION_NET,
        )
    }
}