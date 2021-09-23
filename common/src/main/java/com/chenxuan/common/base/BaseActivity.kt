package com.chenxuan.common.base

import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ToastUtils
import com.chenxuan.common.utils.dialog.ProgressDialog
import kotlinx.coroutines.launch

/**
 * @author cx
 */
abstract class BaseActivity<VM : BaseViewModel<R>, R : BaseRepository, VB : ViewBinding> :
    BaseSimpleActivity<VB>() {
    protected val viewModel: VM by lazy {
        createViewModel()
    }

    override fun initViewModel() {
        super.initViewModel()
        initViewModelActions()
    }

    private fun initViewModelActions() {
        viewModel.statusLiveData.observe(this, { status ->
            status?.run {
                when (this) {
                    CoroutineState.START -> {
                        //协程开始
                    }
                    CoroutineState.REFRESH -> {
                        //协程开始&&进度对话框
                        ProgressDialog.showProgress(this@BaseActivity)
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

        viewModel.errorLiveData.observe(this, {
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