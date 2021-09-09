package com.chenxuan.event

object WatchDog {
    private val iWatchDog: IWatchDog by lazy {
        WatchDogImpl()
    }

    fun watch(target: Any) {
        iWatchDog.watch(target)
    }

    fun unWatch(target: Any) {
        iWatchDog.unWatch(target)
    }

    fun post(event: Any) {
        iWatchDog.post(event)
    }
}