package com.chenxuan.common.utils.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

/**
 * @author cx
 */
fun <T> MutableLiveData<T>.safeObserve(owner: LifecycleOwner, onChange: (T) -> Unit) {
    this.observe(owner) {
        try {
            onChange(it)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}