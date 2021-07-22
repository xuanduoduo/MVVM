package com.chenxuan.business.viewmodel

import androidx.lifecycle.MutableLiveData
import com.chenxuan.common.base.BaseViewModel
import com.chenxuan.business.repository.MainRepository
import com.google.gson.Gson

/**
 * @author cx
 */
class MainViewModel : BaseViewModel<MainRepository>() {
    override fun createRepository() = MainRepository()

    val contentLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getChapters() {
        launch(netBlock = { repository.getChapters() }) {
            contentLiveData.value = Gson().toJson(it.data)
        }
    }
}