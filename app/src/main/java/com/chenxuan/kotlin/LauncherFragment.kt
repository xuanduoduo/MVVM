package com.chenxuan.kotlin

import android.os.Bundle
import com.bumptech.glide.Glide
import com.chenxuan.common.base.BaseSimpleFragment
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.common.utils.router.Router
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.kotlin.databinding.AppFragmentLauncherBinding

/**
 * @author cx
 */
class LauncherFragment : BaseSimpleFragment<AppFragmentLauncherBinding>() {
    companion object {
        const val TAG = "LauncherFragment"

        fun createInstance() = LauncherFragment().apply {

        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {
        binding?.tvLauncher?.setSingleClick {
            Router.startActivity(RouterPath.BUSINESS_FLOW)
        }

        binding?.ivAvatar?.setSingleClick {
            Router.startActivity(RouterPath.BUSINESS_WIDGET)
        }

        Glide
            .with(this)
            .load("https://pic.3gbizhi.com/2014/0430/20140430043839656.jpg")
            .into(binding?.ivAvatar!!)
    }
}