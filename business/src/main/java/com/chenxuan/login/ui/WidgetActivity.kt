package com.chenxuan.login.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseSimpleActivity
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.login.databinding.BusinessWidgetActivityBinding

/**
 * @author cx
 */
@Route(path = RouterPath.LOGIN_WIDGET)
class WidgetActivity : BaseSimpleActivity<BusinessWidgetActivityBinding>() {
    override fun createViewBinding() = BusinessWidgetActivityBinding.inflate(layoutInflater)

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
    }
}