package com.chenxuan.widget.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Looper
import android.os.MessageQueue
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

open class MonitorImage @JvmOverloads constructor(
    context: Context?,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ImageView(context, attributeSet, defStyleAttr, defStyleRes), MessageQueue.IdleHandler {

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        addImageMonitor()
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        addImageMonitor()
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        addImageMonitor()
    }

    override fun setBackgroundDrawable(background: Drawable?) {
        super.setBackgroundDrawable(background)
        addImageMonitor()
    }

    override fun setBackground(background: Drawable?) {
        super.setBackground(background)
        addImageMonitor()
    }

    override fun setBackgroundResource(resid: Int) {
        super.setBackgroundResource(resid)
        addImageMonitor()
    }

    private fun addImageMonitor() {
        Looper.myQueue().removeIdleHandler(this)
        Looper.myQueue().addIdleHandler(this)
    }

    override fun queueIdle(): Boolean {
        val drawable = drawable
        val background = background

        drawable?.run {
            checkDrawable(this)
        }
        background?.run {
            checkDrawable(this)
        }
        return false
    }

    private fun checkDrawable(drawable: Drawable) {
        val width = measuredWidth
        val height = measuredHeight
        val intrinsicWidth = drawable.intrinsicWidth
        val intrinsicHeight = drawable.intrinsicHeight
        val imageSize = calcImageSize(drawable)
        warning(intrinsicWidth, intrinsicHeight, imageSize)
    }

    private fun calcImageSize(drawable: Drawable): Int {
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            return bitmap.byteCount
        }
        val pixelSize = if (drawable.opacity != PixelFormat.OPAQUE) 4 else 2
        return pixelSize * drawable.intrinsicWidth * drawable.intrinsicHeight
    }

    private fun warning(intrinsicWidth: Int, intrinsicHeight: Int, imageSize: Int) {
        Log.d(
            "chenxuan----->",
            "----->intrinsicWidth = $intrinsicWidth, " +
                    "intrinsicHeight = $intrinsicHeight, " +
                    "imageSize = $imageSize"
        )
    }
}