package com.chenxuan.kotlin

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseSimpleActivity
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.common.utils.router.Router
import com.chenxuan.common.utils.router.RouterPath
import kotlinx.android.synthetic.main.activity_launcher.*

/**
 * @author cx
 */
@Route(path = RouterPath.APP_LAUNCHER)
class LauncherActivity : BaseSimpleActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_launcher
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {
        tvLauncher.setSingleClick {
            Router.startActivity(RouterPath.LOGIN_MAIN)
        }
    }
}
