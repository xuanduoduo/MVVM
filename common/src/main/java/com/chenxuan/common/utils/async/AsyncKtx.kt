package com.chenxuan.common.utils.async

import android.os.Handler
import android.os.HandlerThread
import androidx.fragment.app.FragmentActivity

internal val dialogThread = HandlerThread("dialogThread").apply {
    start()
}
internal val dialogHandler = Handler(dialogThread.looper)

fun FragmentActivity.asyncView(
    layout: Int? = null,
    finish: (() -> Unit)? = null,
    block: AsyncHandlerThread.() -> Unit
) = AsyncView(this, layout, finish, block)