package com.chenxuan.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import java.lang.reflect.ParameterizedType

/**
 * @author cx
 */
abstract class BaseSimpleActivity<VB : ViewBinding> : BaseActivity() {
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

    open fun createViewBinding() = reflectViewBinding() as VB

    @Suppress("UNCHECKED_CAST")
    private fun reflectViewBinding(): VB? {
        val types = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        types.forEach {
            if (it.toString().endsWith("Binding") && it is Class<*>) {
                val method = it.getDeclaredMethod("inflate", LayoutInflater::class.java)
                return method.invoke(it, layoutInflater) as VB
            }
        }
        return null
    }
}