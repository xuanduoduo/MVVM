package com.chenxuan.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author cx
 */
abstract class BaseSimpleFragment : Fragment() {
    private lateinit var contentView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentView = inflater.inflate(getLayoutResID(), container, false)
        return contentView
    }

    abstract fun getLayoutResID(): Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initView(savedInstanceState)
        initData(savedInstanceState)
    }

    open fun initViewModel() {}

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initView(savedInstanceState: Bundle?)
}