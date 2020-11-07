package com.chenxuan.common.utils.ktx

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

/**
 * @author cx
 */
inline fun Activity.customDialog(block: AlertDialog.Builder.() -> Unit): AlertDialog =
    AlertDialog.Builder(this)
        .apply {
            block()
        }
        .show()

inline fun Fragment.customDialog(block: AlertDialog.Builder.() -> Unit): AlertDialog =
    AlertDialog.Builder(activity!!)
        .apply {
            block()
        }
        .show()

inline fun AlertDialog.Builder.positiveButton(
    text: String = "确定",
    crossinline block: (dialog: DialogInterface) -> Unit
) {
    this.setPositiveButton(text) { dialog, _ ->
        block(dialog)
    }
}

inline fun AlertDialog.Builder.negativeButton(
    text: String = "取消",
    crossinline block: (dialog: DialogInterface) -> Unit
) {
    this.setNegativeButton(text) { dialog, _ ->
        block(dialog)
    }
}

inline fun AlertDialog.Builder.neutralButton(
    text: String = "知道了",
    crossinline block: (dialog: DialogInterface) -> Unit
) {
    this.setNeutralButton(text) { dialog, _ ->
        block(dialog)
    }
}