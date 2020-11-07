package com.chenxuan.login.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseSimpleActivity
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.login.R

/**
 * @author cx
 */
@Route(path = RouterPath.LOGIN_WIDGET)
class WidgetActivity : BaseSimpleActivity() {
    override fun getContentView() = R.layout.login_activity_widget

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
    }
}