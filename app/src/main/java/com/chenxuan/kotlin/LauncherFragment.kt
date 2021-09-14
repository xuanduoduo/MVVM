package com.chenxuan.kotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.chenxuan.common.base.BaseSimpleFragment
import com.chenxuan.common.utils.ktx.setSingleClick
import com.chenxuan.kotlin.databinding.AppFragmentLauncherBinding

class LauncherFragment : BaseSimpleFragment<AppFragmentLauncherBinding>() {
    companion object {
        const val TAG = "LauncherFragment"

        fun createInstance() = LauncherFragment().apply {

        }
    }

    override fun createViewBinding(inflate: LayoutInflater) =
        AppFragmentLauncherBinding.inflate(inflate)

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initView(savedInstanceState: Bundle?) {
        binding?.tvLauncher?.setSingleClick {
            Log.d("chenxuan----->", "Launcher")
        }
    }
}