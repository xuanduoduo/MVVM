package com.chenxuan.event

interface IWatchDog {
    fun watch(target: Any)

    fun unWatch(target: Any)

    fun post(event: Any)
}