package com.chenxuan.common.utils.async

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.chenxuan.common.utils.ktx.mainThread

fun Activity.asyncDialog(block: AlertDialog.Builder.() -> Unit) {
    dialogHandler.post {
        AlertDialog.Builder(this)
            .apply {
                block()
            }
            .show()
    }
}

fun Fragment.asyncDialog(block: AlertDialog.Builder.() -> Unit) {
    dialogHandler.post {
        AlertDialog.Builder(requireActivity())
            .apply {
                block()
            }
            .show()
    }
}

inline fun AlertDialog.Builder.positiveOnUiThread(
    text: String = "确定",
    crossinline block: (dialog: DialogInterface) -> Unit
) {
    this.setPositiveButton(text) { dialog, _ ->
        dialog.dismiss()
        mainThread {
            block(dialog)
        }
    }
}

inline fun AlertDialog.Builder.negativeOnUiThread(
    text: String = "取消",
    crossinline block: (dialog: DialogInterface) -> Unit
) {
    this.setNegativeButton(text) { dialog, _ ->
        dialog.dismiss()
        mainThread {
            block(dialog)
        }
    }
}

inline fun AlertDialog.Builder.neutralOnUiThread(
    text: String = "知道了",
    crossinline block: (dialog: DialogInterface) -> Unit
) {
    this.setNeutralButton(text) { dialog, _ ->
        dialog.dismiss()
        mainThread {
            block(dialog)
        }
    }
}
