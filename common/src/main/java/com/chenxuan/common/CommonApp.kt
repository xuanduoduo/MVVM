package com.chenxuan.common

import android.app.Application
import com.chenxuan.base.IApp
import com.google.auto.service.AutoService

/**
 * @author cx
 */
@AutoService(com.chenxuan.base.IApp::class)
class CommonApp : IApp {
    override fun onCreate(app: Application) {
    }
}