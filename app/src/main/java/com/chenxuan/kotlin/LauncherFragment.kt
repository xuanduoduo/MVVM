package com.chenxuan.kotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.chenxuan.common.base.BaseSimpleFragment
import com.chenxuan.common.sync.BLock
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
        initThread()
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding?.tvLauncher?.setSingleClick {
            Log.d("chenxuan----->", "Launcher")
        }

        Glide
            .with(this)
            .load("https://pic.3gbizhi.com/2014/0430/20140430043839656.jpg")
            .into(binding?.iv!!)
    }

    private fun initThread() {
        val lock = BLock()
        var count = 0
        val maxCount = 100
        Thread A@{
            while (true) {
                lock.lock()
                if (count > maxCount) {
                    lock.unlock()
                    return@A
                }
                if (count % 3 == 0) {
                    Log.d("chenxuan----->", "A-$count")
                    count++
                }
                lock.unlock()
            }
        }.start()

        Thread B@{
            while (true) {
                lock.lock()
                if (count > maxCount) {
                    lock.unlock()
                    return@B
                }
                if (count % 3 == 1) {
                    Log.d("chenxuan----->", "B-$count")
                    count++
                }
                lock.unlock()
            }
        }.start()

        Thread C@{
            while (true) {
                lock.lock()
                if (count > maxCount) {
                    lock.unlock()
                    return@C
                }
                if (count % 3 == 2) {
                    Log.d("chenxuan----->", "C-$count")
                    count++
                }
                lock.unlock()
            }
        }.start()
    }
}