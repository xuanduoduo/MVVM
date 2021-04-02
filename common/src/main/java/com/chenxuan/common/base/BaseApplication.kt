package com.chenxuan.common.base

import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.chenxuan.common.BuildConfig
import com.didichuxing.doraemonkit.DoraemonKit
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * @author cx
 */
open class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        //初始化logger
        Logger.addLogAdapter(AndroidLogAdapter())
        //初始化router
        initARouter()
        //初始化dokit
        DoraemonKit.install(this)
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {   // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()      // 打印日志
            ARouter.openDebug()    // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)// 尽可能早，推荐在Application中初始化
    }
}