package com.chenxuan.widget.dsl

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * @author cx
 */

inline fun Activity.contentView(block: Activity.() -> View) {
    this.setContentView(block())
}

inline fun Activity.linearLayout(block: LinearLayout.() -> Unit): View {
    return LinearLayout(this).apply {
        block()
    }
}

inline fun <reified T : ViewGroup> Activity.viewGroup(block: T.() -> Unit): View {
    val constructor = T::class.java.getConstructor(Context::class.java)
    return constructor.newInstance(this).apply {
        block()
    }
}

inline fun ViewGroup.text(
    content: ObserverField<String>? = null,
    width: Int = 0,
    height: Int = 0,
    block: TextView.() -> Unit
) {
    TextView(this.context).apply {
        if (width != 0 && height != 0) {
            val params = ViewGroup.MarginLayoutParams(width, height)
            if (width != 0) params.width = width
            if (height != 0) params.height = height
            layoutParams = params
        }
        content?.run {
            text = content.value
            setOnChangeListener {
                text = content.value
            }
        }
        block()
        addView(this)
    }
}

inline fun <reified T : View> ViewGroup.view(width: Int = 0, height: Int = 0, block: T.() -> Unit) {
    val constructor = T::class.java.getConstructor(Context::class.java)
    (constructor.newInstance(this.context) as T).apply {
        if (width != 0 && height != 0) {
            val params = ViewGroup.MarginLayoutParams(width, height)
            if (width != 0) params.width = width
            if (height != 0) params.height = height
            layoutParams = params
        }
        block()
        addView(this)
    }
}

inline fun View.onClick(crossinline block: (View) -> Unit) {
    this.setOnClickListener {
        it?.run {
            block(this)
        }
    }
}

inline fun View.layoutParams(block: ViewGroup.MarginLayoutParams.() -> Unit) {
    val params = layoutParams ?: ViewGroup.MarginLayoutParams(width, height)
    (params as ViewGroup.MarginLayoutParams).block()
    layoutParams = params
}

fun View.setTopMargin(margin: Int) {
    val params = layoutParams ?: ViewGroup.MarginLayoutParams(width, height)
    (params as ViewGroup.MarginLayoutParams).topMargin = 300
    layoutParams = params
}