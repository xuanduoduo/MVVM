package com.chenxuan.base

import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.didichuxing.doraemonkit.DoraemonKit
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.tencent.mmkv.MMKV

/**
 * @author cx
 */
class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        IApp.init(this)
        super.onCreate()
        initARouter()
        Logger.addLogAdapter(AndroidLogAdapter())
        DoraemonKit.install(this)
        MMKV.initialize(this)
        initStrictMode()
    }

    private fun initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )

            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}