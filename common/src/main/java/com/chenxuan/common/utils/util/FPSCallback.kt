package com.chenxuan.common.utils.util

import android.view.Choreographer

fun postFPSCallback() {
    val fpsCallback = FPSCallback(System.nanoTime()) {
        val fps = it
    }
    Choreographer.getInstance().postFrameCallback(fpsCallback)
}

class FPSCallback(var currentTime: Long, var callback: (Int) -> Unit) :
    Choreographer.FrameCallback {
    private var frameCount = 0
    private var targetTime = 1000 * 1000000

    override fun doFrame(frameTimes: Long) {
        frameCount++
        if (frameTimes - currentTime < targetTime) {
            Choreographer.getInstance().postFrameCallback(this)
        } else {
            callback(frameCount)
        }
    }
}