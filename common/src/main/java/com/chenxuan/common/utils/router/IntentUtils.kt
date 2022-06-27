package com.chenxuan.common.utils.router

import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.chenxuan.common.utils.ktx.andPermission
import com.yanzhenjie.permission.runtime.Permission

/**
 * @author cx
 */
object IntentUtils {
    fun gotoScanActivity() {
        ActivityUtils.getTopActivity().andPermission(Permission.CAMERA) {
            onGranted {
                Router.startActivity(RouterPath.BUSINESS_SCAN)
            }
            onDenied {
                ToastUtils.showShort("请打开摄像头权限")
            }
        }
    }
}