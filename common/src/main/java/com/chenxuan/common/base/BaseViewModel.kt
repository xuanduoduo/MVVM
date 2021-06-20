package com.chenxuan.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @author cx
 */
abstract class BaseViewModel<R : BaseRepository> : ViewModel() {
    protected val repository by lazy {
        createRepository()
    }

    abstract fun createRepository(): R

    /**
     * 协程状态管理
     *
     * 开始 CoroutineState.START
     * 刷新 CoroutineState.REFRESH
     * 结束 CoroutineState.FINISH
     * 异常 CoroutineState.ERROR
     */
    val statusLiveData: MutableLiveData<CoroutineState> by lazy {
        MutableLiveData<CoroutineState>()
    }

    val errorLiveData: MutableLiveData<Exception> by lazy {
        MutableLiveData<Exception>()
    }

    fun <T> launch(
        refresh: Boolean = true,
        netBlock: NetBlock<T>,
        error: Error? = null,
        success: Success<T>
    ) {
        viewModelScope.launch {
            try {
                if (refresh) {
                    statusLiveData.value = CoroutineState.REFRESH
                } else {
                    statusLiveData.value = CoroutineState.START
                }
                val result = netBlock()
                statusLiveData.value = CoroutineState.FINISH
                success(result)
            } catch (e: Exception) {
                if (error == null) {
                    statusLiveData.value = CoroutineState.ERROR
                    errorLiveData.value = e
                } else error(e)
            }
        }
    }
}