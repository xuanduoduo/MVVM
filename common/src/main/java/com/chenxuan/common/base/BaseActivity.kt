package com.chenxuan.common.base

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ToastUtils
import com.chenxuan.common.utils.common.ProgressDialog
import kotlinx.coroutines.launch

/**
 * @author cx
 */
abstract class BaseActivity<V : BaseRepository, T : BaseViewModel<V>> : BaseSimpleActivity() {
    protected val viewModel: T by lazy {
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

        viewModel.errorLiveData.observe(this, Observer {
            ToastUtils.showShort(it.message)
        })
    }

    abstract fun createViewModel(): T

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