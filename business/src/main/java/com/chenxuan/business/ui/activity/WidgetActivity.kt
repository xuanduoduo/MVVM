package com.chenxuan.business.ui.activity

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseActivity
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.widget.dsl.*

/**
 * @author cx
 */
@Route(path = RouterPath.BUSINESS_WIDGET)
class WidgetActivity : BaseActivity() {
    private val content = ObserverField("onClick")
    private var clickNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView {
            viewGroup<LinearLayout> {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER_HORIZONTAL
                view<TextView>(width = 500, height = 200) {
                    setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
                    setTextColor(resources.getColor(android.R.color.black))
                    gravity = Gravity.CENTER
                    text = "kotlin DSL"
                    setTopMargin(100)

                    onClick {
                        content.setState("onClick--->${++clickNum}")
                    }
                }

                text(content = content, width = 700, height = 200) {
                    gravity = Gravity.CENTER
                    setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
                    setTextColor(resources.getColor(android.R.color.black))
                    setTopMargin(200)

                    onClick {
                        content.setState("onClick")
                        clickNum = 0
                    }
                }
            }
        }
    }
}