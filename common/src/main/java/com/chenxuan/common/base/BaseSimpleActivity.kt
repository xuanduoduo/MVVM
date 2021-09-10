package com.chenxuan.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar

/**
 * @author cx
 */
abstract class BaseSimpleActivity<VB : ViewBinding> : AppCompatActivity() {
    protected val binding by lazy {
        createViewBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewModel()
        initImmersionBar()
        initView(savedInstanceState)
        initData(savedInstanceState)
    }

    private fun initImmersionBar() {
        immersionBar {
            transparentStatusBar()
            statusBarDarkFont(true)
            fitsSystemWindows(true)
            hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
        }
    }

    open fun initViewModel() {}

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun createViewBinding(): VB
}