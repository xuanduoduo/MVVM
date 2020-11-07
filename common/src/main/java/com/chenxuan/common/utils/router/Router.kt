package com.chenxuan.common.utils.router

import android.os.Bundle
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author cx
 */
object Router {
    fun startActivity(url: String) {
        if (TextUtils.isEmpty(url)) return
        ARouter.getInstance().build(url).navigation()
    }

    fun startActivity(url: String, bundle: Bundle) {
        if (TextUtils.isEmpty(url)) return
        ARouter.getInstance().build(url).with(bundle).navigation()
    }

    fun startActivityWithGreen(url: String) {
        if (TextUtils.isEmpty(url)) return
        ARouter.getInstance().build(url).greenChannel().navigation()
    }
}