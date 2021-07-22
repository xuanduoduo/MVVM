package com.chenxuan.business.ui

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.chenxuan.common.base.BaseSimpleActivity
import com.chenxuan.common.utils.router.RouterPath
import com.chenxuan.business.databinding.BusinessScanActivityBinding
import com.google.zxing.client.result.ParsedResultType
import com.mylhyl.zxing.scanner.ScannerOptions

/**
 * @author cx
 */
@Route(path = RouterPath.BUSINESS_SCAN)
class ScanActivity : BaseSimpleActivity<BusinessScanActivityBinding>() {
    override fun createViewBinding() = BusinessScanActivityBinding.inflate(layoutInflater)

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        val options = ScannerOptions.Builder()
        options.setFrameCornerHide(true)
        options.setFrameHide(true)
        options.setScanFullScreen(true)
        binding.scannerView.setScannerOptions(options.build())
        binding.scannerView.setOnScannerCompletionListener { rawResult, parsedResult, barcode ->
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
        binding.scannerView.onResume()
        super.onResume()
    }

    override fun onPause() {
        binding.scannerView.onPause()
        super.onPause()
    }
}