package com.chenxuan.common.utils.ktx

import android.view.View

/**
 * @author cx
 */
inline fun View.setSingleClick(
    targetTimeMills: Long = 500,
    crossinline onclick: (v: View?) -> Unit
) {
    var lastTime = 0L
    this.setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime > targetTimeMills) {
            onclick.invoke(it)
        }
        lastTime = currentTime
    }
}