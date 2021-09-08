package com.chenxuan.event

@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class Watch(val model: WatchModel = WatchModel.NORMAL)
