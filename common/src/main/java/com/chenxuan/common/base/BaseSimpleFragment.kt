package com.chenxuan.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author cx
 */
abstract class BaseSimpleFragment<VB : ViewBinding> : BaseFragment() {
    protected var binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createViewBinding()
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initView(savedInstanceState)
        initData(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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