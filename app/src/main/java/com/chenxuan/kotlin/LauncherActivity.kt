package com.chenxuan.kotlin

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseSimpleActivity
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.common.utils.router.Router
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.kotlin.databinding.AppActivityLauncherBinding

/**
 * @author cx
 */
@Route(path = RouterPath.APP_LAUNCHER)
class LauncherActivity : BaseSimpleActivity<AppActivityLauncherBinding>() {
    override fun createViewBinding() = AppActivityLauncherBinding.inflate(layoutInflater)

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.flContainer,
                LauncherFragment.createInstance(),
                LauncherFragment.TAG
            )
            commitAllowingStateLoss()
        }


        binding.tvBusiness.setSingleClick {
            Router.startActivity(RouterPath.BUSINESS_MAIN)
        }
    }
}
