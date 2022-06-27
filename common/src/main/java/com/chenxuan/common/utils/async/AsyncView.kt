package com.chenxuan.common.utils.async

import android.view.View
import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

/**
 * @author cx
 */
object AsyncView {
    operator fun invoke(
        activity: FragmentActivity,
        layout: Int? = null,
        finish: (() -> Unit)? = null,
        block: AsyncHandlerThread.() -> Unit
    ) = object : AsyncHandlerThread(WeakReference(activity)) {
        override fun getLayoutId(): Int {
            return layout ?: super.getLayoutId()
        }

        override fun onInflated(root: View?) {
            block()
        }

        override fun onFinish() {
            finish?.invoke()
        }
    }.apply { inflate() }
}