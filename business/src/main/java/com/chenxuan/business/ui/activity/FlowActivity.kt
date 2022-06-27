package com.chenxuan.business.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.business.R
import com.chenxuan.business.databinding.BusinessActivityFlowBinding
import com.chenxuan.business.repository.MainRepository
import com.chenxuan.business.ui.fragment.FlowFragment
import com.chenxuan.business.viewmodel.FlowViewModel
import com.chenxuan.common.base.BaseVMActivity
import com.chenxuan.common.utils.router.RouterPath

/**
 * @author cx
 */
@Route(path = RouterPath.BUSINESS_FLOW)
class FlowActivity :
    BaseVMActivity<FlowViewModel, MainRepository, BusinessActivityFlowBinding>() {
    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.businessFlowContain,
                FlowFragment.createInstance(),
                FlowFragment.TAG
            )
            commitAllowingStateLoss()
        }
    }
}