package com.chenxuan.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.chenxuan.common.base.BaseViewModel
import com.chenxuan.login.repository.LoginRepository
import com.google.gson.Gson

/**
 * @author cx
 */
class LoginViewModel : BaseViewModel<LoginRepository>() {
    override fun createRepository() = LoginRepository()

    val contentLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getChapters() {
        launch(netBlock = { repository.getChapters() }) {
            contentLiveData.value = Gson().toJson(it.data)
        }
    }
}