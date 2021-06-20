package com.chenxuan.login.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseActivity
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.login.databinding.LoginActivityLoginBinding
import com.chenxuan.login.repository.LoginRepository
import com.chenxuan.login.viewmodel.LoginViewModel
import com.google.gson.Gson

/**
 * @author cx
 */
@Route(path = RouterPath.LOGIN_MAIN)
class LoginActivity : BaseActivity<LoginViewModel, LoginRepository, LoginActivityLoginBinding>() {
    override fun createViewBinding() = LoginActivityLoginBinding.inflate(layoutInflater)

    override fun createViewModel() = ViewModelProvider(this)[LoginViewModel::class.java]

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.contentLiveData.observe(this) { content ->
            binding.tvLogin.text = Gson().toJson(content)
        }

        binding.tvLogin.setSingleClick {
            viewModel.getChapters()
        }
    }
}
