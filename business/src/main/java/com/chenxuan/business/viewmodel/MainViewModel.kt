package com.chenxuan.business.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.chenxuan.business.repository.MainRepository
import com.chenxuan.common.base.BaseViewModel
import com.chenxuan.common.launch.error
import com.chenxuan.common.launch.success
import com.chenxuan.common.utils.util.toJsonString

/**
 * @author cx
 */
class MainViewModel : BaseViewModel<MainRepository>() {
    override fun createRepository() = MainRepository()

    val contentLiveData by lazy {
        MutableLiveData<String>()
    }

    fun getChapters() {
        request {
            repository.getChapters()
        } success {
            contentLiveData.value = it.response.data.toJsonString()
        } error {
            ToastUtils.showShort(it.error.message)
        }
    }
}