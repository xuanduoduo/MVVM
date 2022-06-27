package com.chenxuan.business.ui.fragment

import android.os.Bundle
import com.chenxuan.business.databinding.BusinessFragmentFlowBinding
import com.chenxuan.business.repository.MainRepository
import com.chenxuan.business.viewmodel.FlowViewModel
import com.chenxuan.common.base.BaseVMFragment
import com.chenxuan.common.utils.ktx.setSingleClick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn

/**
 * @author cx
 */
class FlowFragment : BaseVMFragment<FlowViewModel, MainRepository, BusinessFragmentFlowBinding>() {
    companion object {
        const val TAG = "FlowFragment"

        fun createInstance() = FlowFragment().apply {

        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        binding?.tvFlow?.setSingleClick {
            viewModel.getChapters()
        }

    }

    override fun initView(savedInstanceState: Bundle?) {
        launch {
            viewModel.stateFlow.filter {
                it.isNotEmpty()
            }.flowOn(Dispatchers.IO).collect {
                binding?.tvFlow?.text = it
            }
        }
    }
}