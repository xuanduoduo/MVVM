package com.chenxuan.widget.utils

import com.chenxuan.widget.CustomToolbar

/**
 * @author cx
 */
inline fun CustomToolbar.init(block: CustomToolbar.() -> Unit) {
    block()
}