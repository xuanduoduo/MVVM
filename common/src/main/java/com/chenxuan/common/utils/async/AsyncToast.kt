package com.chenxuan.common.utils.async

import android.content.Context
import android.widget.Toast

fun Context.asyncShortToast(text: String) {
    dialogHandler.post {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}

fun Context.asyncShortToast(resId: Int) {
    dialogHandler.post {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }
}

fun Context.asyncLongToast(text: String) {
    dialogHandler.post {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}

fun Context.asyncLongToast(resId: Int) {
    dialogHandler.post {
        Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
    }
}