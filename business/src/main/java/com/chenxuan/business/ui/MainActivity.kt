package com.chenxuan.business.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.business.databinding.BusinessMainActivityBinding
import com.chenxuan.business.repository.MainRepository
import com.chenxuan.business.viewmodel.MainViewModel
import com.chenxuan.common.base.BaseActivity
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.common.utils.router.RouterPath
import com.google.gson.Gson

/**
 * @author cx
 */
@Route(path = RouterPath.BUSINESS_MAIN)
class MainActivity :
    BaseActivity<MainViewModel, MainRepository, BusinessMainActivityBinding>() {
    override fun createViewBinding() = BusinessMainActivityBinding.inflate(layoutInflater)

    override fun createViewModel() = ViewModelProvider(this)[MainViewModel::class.java]

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.contentLiveData.observe(this) { content ->
            binding.tvBusiness.text = Gson().toJson(content)
        }

        binding.tvBusiness.setSingleClick {
            viewModel.getChapters()
        }
    }
}