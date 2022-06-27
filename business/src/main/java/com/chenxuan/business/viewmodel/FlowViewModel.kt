package com.chenxuan.business.viewmodel

import com.chenxuan.business.repository.MainRepository
import com.chenxuan.common.base.BaseViewModel
import com.chenxuan.common.launch.success
import com.chenxuan.common.utils.util.toJsonString
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @author cx
 */
class FlowViewModel : BaseViewModel<MainRepository>() {
    override fun createRepository() = MainRepository()

    val stateFlow = MutableStateFlow("")

    fun getChapters() {
        request {
            repository.getChapters()
        } success {
            stateFlow.value = it.response.data.toJsonString()
        }
    }
}