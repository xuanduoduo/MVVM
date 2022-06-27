package com.chenxuan.business.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.business.databinding.BusinessActivityMainBinding
import com.chenxuan.business.repository.MainRepository
import com.chenxuan.business.viewmodel.MainViewModel
import com.chenxuan.common.base.BaseVMActivity
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.common.utils.util.safeObserve

/**
 * @author cx
 */
@Route(path = RouterPath.BUSINESS_MAIN)
class MainActivity :
    BaseVMActivity<MainViewModel, MainRepository, BusinessActivityMainBinding>() {
    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.contentLiveData.safeObserve(this) { content ->
            binding.tvBusiness.text = content
        }

        binding.tvBusiness.setSingleClick {
            viewModel.getChapters()
        }
    }
}