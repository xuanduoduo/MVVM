package com.chenxuan.event

import android.os.Looper
import androidx.lifecycle.LifecycleOwner

fun LifecycleOwner.watch() {
    WatchDog.watch(this)
}

internal fun checkThread() = Looper.myLooper() == Looper.getMainLooper()