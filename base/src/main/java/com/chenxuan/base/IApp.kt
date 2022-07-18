package com.chenxuan.base

import android.app.Application
import java.util.*

/**
 * @author cx
 */
interface IApp {
    fun onCreate(app: Application)

    companion object {
        fun init(application: Application) {
            val apps = ServiceLoader.load(IApp::class.java)
            apps.forEach {
                it.onCreate(application)
            }
        }
    }
}