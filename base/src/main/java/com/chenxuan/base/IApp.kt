package com.chenxuan.base

import android.app.Application

/**
 * @author cx
 */
interface IApp {
    fun onCreate(app: Application)

    companion object {
        fun init(application: Application) {
            AppPath.appPaths.forEach {
                (Class.forName(it).newInstance() as IApp).onCreate(application)
            }
        }
    }
}