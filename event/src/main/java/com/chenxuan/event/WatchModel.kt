package com.chenxuan.event

enum class WatchModel {
    /**
     * 事件发出线程
     */
    NORMAL,

    /**
     * 主线程
     */
    MAIN,

    /**
     * 异步线程
     */
    ASYNC
}