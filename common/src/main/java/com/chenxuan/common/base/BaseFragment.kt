package com.chenxuan.common.base

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ToastUtils
import com.chenxuan.common.utils.common.ProgressDialog
import kotlinx.coroutines.launch

/**
 * @author cx
 */
abstract class BaseFragment<VM : BaseViewModel<R>, R : BaseRepository, VB : ViewBinding> :
    BaseSimpleFragment<VB>() {
    protected val viewModel: VM by lazy {
        createViewModel()
    }

    override fun initViewModel() {
        super.initViewModel()
        initViewModelActions()
    }

    private fun initViewModelActions() {
        viewModel.statusLiveData.observe(this, Observer { status ->
            status?.run {
                when (this) {
                    CoroutineState.START -> {
                        //协程开始
                    }
                    CoroutineState.REFRESH -> {
                        //协程开始&&进度对话框
                        activity?.run {
                            ProgressDialog.showProgress(this)
                        }
                    }
                    CoroutineState.FINISH -> {
                        //协程结束
                        ProgressDialog.dismissProgress()
                    }
                    CoroutineState.ERROR -> {
                        //协程异常
                        ProgressDialog.dismissProgress()
                    }
                }
            }
        })

        viewModel.errorLiveData.observe(this, Observer {
            ToastUtils.showShort(it.message)
        })
    }

    abstract fun createViewModel(): VM

    fun launch(block: Block) {
        lifecycleScope.launch {
            try {
                block()
            } catch (e: Exception) {
                //异常处理
            }
        }
    }
}