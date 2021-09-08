package com.chenxuan.event

import androidx.lifecycle.LifecycleOwner

fun LifecycleOwner.watch() {
    WatchDog.watch(this)
}