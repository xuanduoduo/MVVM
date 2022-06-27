package com.chenxuan.net

import android.app.Application
import com.chenxuan.base.IApp

/**
 * @author cx
 */
class NetApp : IApp {
    override fun onCreate(app: Application) {
        ApiUtil.init()
    }
}