package com.chenxuan.common.base

/**
 * @author cx
 */
abstract class BaseViewModel<R : BaseRepository> : BaseSimpleViewModel() {
    protected val repository by lazy {
        createRepository()
    }

    abstract fun createRepository(): R
}