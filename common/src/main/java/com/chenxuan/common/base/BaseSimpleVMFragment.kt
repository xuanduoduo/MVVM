package com.chenxuan.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ToastUtils
import com.chenxuan.common.utils.util.ProgressDialog
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

/**
 * @author cx
 */
abstract class BaseSimpleVMFragment<VM : BaseSimpleViewModel, VB : ViewBinding> :
    BaseSimpleFragment<VB>() {
    protected val viewModel: VM by lazy {
        createViewModel()
    }

    override fun initViewModel() {
        super.initViewModel()
        initViewModelActions()
    }

    private fun initViewModelActions() {
        viewModel.statusLiveData.observe(this) { status ->
            status?.run {
                when (this) {
                    CoroutineState.START -> {
                        //START
                    }
                    CoroutineState.REFRESH -> {
                        //REFRESH
                        activity?.run {
                            ProgressDialog.showProgress(this)
                        }
                    }
                    CoroutineState.FINISH -> {
                        //FINISH
                        ProgressDialog.dismissProgress()
                    }
                    CoroutineState.ERROR -> {
                        //ERROR
                        ProgressDialog.dismissProgress()
                    }
                }
            }
        }

        viewModel.errorLiveData.observe(this) {
            ToastUtils.showShort(it.message)
        }
    }

    fun launch(block: Launch) {
        lifecycleScope.launch {
            try {
                block()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    open fun createViewModel() = reflectViewModel()

    @Suppress("UNCHECKED_CAST")
    private fun reflectViewModel(): VM {
        val types = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        return ViewModelProvider(this)[types[0] as Class<ViewModel>] as VM
    }
}