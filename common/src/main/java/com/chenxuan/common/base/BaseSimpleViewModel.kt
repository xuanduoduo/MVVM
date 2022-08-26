package com.chenxuan.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chenxuan.common.launch.LaunchResult
import com.chenxuan.common.launch.RequestHandler
import kotlinx.coroutines.launch

/**
 * @author cx
 */
abstract class BaseSimpleViewModel : ViewModel() {
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

    val errorLiveData: MutableLiveData<Throwable> by lazy {
        MutableLiveData<Throwable>()
    }

    fun <T> request(
        refresh: Boolean = true,
        block: NetBlock<T>,
    ): RequestHandler<T> {
        val requestHandler = RequestHandler<T>()
        viewModelScope.launch {
            runCatching {
                if (refresh) {
                    statusLiveData.value = CoroutineState.REFRESH
                } else {
                    statusLiveData.value = CoroutineState.START
                }
                block()
            }.onSuccess {
                statusLiveData.value = CoroutineState.FINISH
                requestHandler.successListener?.invoke(
                    LaunchResult.Success(it)
                )
            }.onFailure {
                statusLiveData.value = CoroutineState.ERROR
                if (requestHandler.errorListener == null) {
                    errorLiveData.value = it
                } else requestHandler.errorListener?.invoke(LaunchResult.Error(it))
            }
        }
        return requestHandler
    }

    fun launch(block: Launch) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}