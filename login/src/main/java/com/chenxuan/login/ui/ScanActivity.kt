package com.chenxuan.login.ui

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseSimpleActivity
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.login.R
import com.google.zxing.client.result.ParsedResultType
import com.mylhyl.zxing.scanner.ScannerOptions
import kotlinx.android.synthetic.main.login_activity_scan.*

/**
 * @author cx
 */
@Route(path = RouterPath.LOGIN_SCAN)
class ScanActivity : BaseSimpleActivity() {
    override fun getContentView() = R.layout.login_activity_scan

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        val options = ScannerOptions.Builder()
        options.setFrameCornerHide(true)
        options.setFrameHide(true)
        options.setScanFullScreen(true)
        scanner_view.setScannerOptions(options.build())
        scanner_view.setOnScannerCompletionListener { rawResult, parsedResult, barcode ->
            when (parsedResult.type) {
                ParsedResultType.ADDRESSBOOK -> {
                }
                ParsedResultType.URI -> {
                }
                ParsedResultType.TEXT -> Toast.makeText(
                    this,
                    rawResult.text,
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                }
            }
            finish()
        }
    }

    override fun onResume() {
        scanner_view.onResume()
        super.onResume()
    }

    override fun onPause() {
        scanner_view.onPause()
        super.onPause()
    }
}