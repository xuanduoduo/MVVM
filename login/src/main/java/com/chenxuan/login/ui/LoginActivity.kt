package com.chenxuan.login.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseActivity
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.login.R
import com.chenxuan.login.repository.LoginRepository
import com.chenxuan.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_activity_login.*

/**
 * @author cx
 */
@Route(path = RouterPath.LOGIN_MAIN)
class LoginActivity : BaseActivity<LoginRepository, LoginViewModel>() {
    override fun createViewModel() = ViewModelProvider(this)[LoginViewModel::class.java]

    override fun getContentView() = R.layout.login_activity_login

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.contentLiveData.observe(this, Observer<String> { content ->
            tvLogin.text = content
        })

        tvLogin.setSingleClick {
            viewModel.getChapters()
        }
    }
}
