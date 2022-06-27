package com.chenxuan.business.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.business.R
import com.chenxuan.common.base.BaseActivity
import com.chenxuan.common.utils.async.asyncView
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.common.utils.router.RouterPath

/**
 * @author cx
 */
@Route(path = RouterPath.BUSINESS_ASYNC)
class AsyncActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        asyncView(R.layout.business_activity_async) {
            val tv: TextView? = bindView(R.id.tvAsync)
            tv?.text = "chenxuan"
            tv?.setSingleClick {
                tv.text = "chenxuan"
            }

            postDelay(3000) {
                tv?.text = "async"
            }
        }
    }
}