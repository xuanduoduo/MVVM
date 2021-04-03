package com.chenxuan.net

import com.chenxuan.base.BaseContentProvider

/**
 * @author cx
 */
class NetContentProvider : BaseContentProvider() {
    override fun onCreate(): Boolean {
        Api.init()
        return true
    }
}