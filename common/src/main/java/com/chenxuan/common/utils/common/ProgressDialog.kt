package com.chenxuan.common.utils.common

import android.annotation.SuppressLint
import android.app.Activity
import com.afollestad.materialdialogs.GravityEnum
import com.afollestad.materialdialogs.MaterialDialog

/**
 * @author cx
 */
object ProgressDialog {
    @SuppressLint("StaticFieldLeak")
    private var materialDialog: MaterialDialog? = null

    fun showProgress(activity: Activity, msg: String = "请稍候...") {
        showProgressDialog(activity, msg)
    }

    private fun showProgressDialog(activity: Activity, msg: String) {
        materialDialog = MaterialDialog.Builder(activity)
            .content(msg)
            .cancelable(false)
            .canceledOnTouchOutside(false)
            .contentGravity(GravityEnum.CENTER)
            .progress(true, 0)
            .progressIndeterminateStyle(false)
            .show()
    }

    fun dismissProgress() {
        materialDialog?.dismiss()
        materialDialog = null
    }
}