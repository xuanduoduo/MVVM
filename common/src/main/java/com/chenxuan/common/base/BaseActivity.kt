package com.chenxuan.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author cx
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}