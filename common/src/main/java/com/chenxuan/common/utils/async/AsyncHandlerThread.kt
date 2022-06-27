package com.chenxuan.common.utils.async

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.chenxuan.common.R
import java.lang.ref.WeakReference

/**
 * @author cx
 */
abstract class AsyncHandlerThread @JvmOverloads constructor(
    private val asyncActivity: WeakReference<FragmentActivity>,
    name: String = "AsyncHandlerThread"
) :
    HandlerThread(name), LifecycleEventObserver {
    private var handler: H? = null

    fun inflate() {
        start()
        handler = H(looper, asyncActivity)
        handler?.sendEmptyMessage(INFLATE_UI)
        asyncActivity.get()?.lifecycle?.addObserver(this)
    }

    fun <T : View> bindView(id: Int): T? {
        return handler?.root?.findViewById(id) as T?
    }

    fun post(block: () -> Unit) {
        handler?.post {
            block()
        }
    }

    fun postDelay(mills: Long, block: () -> Unit) {
        handler?.postDelayed({
            block()
        }, mills)
    }

    open fun getLayoutId() = R.layout.common_view_empty

    abstract fun onInflated(root: View?)

    abstract fun onFinish()

    private inner class H(
        looper: Looper,
        private val asyncActivity: WeakReference<FragmentActivity>
    ) :
        Handler(looper) {
        var root: View? = null
        var wm: WindowManager? = null

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                INFLATE_UI -> {
                    root = LayoutInflater.from(asyncActivity.get()).inflate(getLayoutId(), null)
                    wm = asyncActivity.get()
                        ?.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
                    val param = WindowManager.LayoutParams()
                    param.width = WindowManager.LayoutParams.MATCH_PARENT
                    param.height = WindowManager.LayoutParams.MATCH_PARENT
                    wm?.addView(root, param)

                    root?.run {
                        isFocusable = true
                        isFocusableInTouchMode = true
                        requestFocus()

                        setOnKeyListener { _, keyCode, event ->
                            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                                onStop()
                                true
                            } else {
                                false
                            }
                        }
                    }

                    onInflated(root)
                }
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            quitSafely()
            onFinish()
        }
    }

    open fun onStop() {
        handler?.run {
            removeCallbacksAndMessages(null)
            wm?.removeViewImmediate(root)
            root = null
            wm = null
        }
        handler = null
        asyncActivity.get()?.finish()
    }

    companion object {
        private const val INFLATE_UI = 10001
    }
}