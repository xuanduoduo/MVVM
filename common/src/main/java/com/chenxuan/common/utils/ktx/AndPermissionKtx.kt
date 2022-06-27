package com.chenxuan.common.utils.ktx

import android.app.Activity
import androidx.fragment.app.Fragment
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.PermissionRequest

/**
 * @author cx
 */
inline fun Activity.andPermission(permission: String, block: PermissionRequest.() -> Unit) =
    AndPermission
        .with(this)
        .runtime()
        .permission(permission)
        .apply {
            block()
        }
        .start()

inline fun Activity.andPermission(permissions: Array<String>, block: PermissionRequest.() -> Unit) =
    AndPermission
        .with(this)
        .runtime()
        .permission(permissions)
        .apply {
            block()
        }
        .start()

inline fun Fragment.andPermission(permission: String, block: PermissionRequest.() -> Unit) =
    AndPermission
        .with(this)
        .runtime()
        .permission(permission)
        .apply {
            block()
        }
        .start()

inline fun Fragment.andPermission(permissions: Array<String>, block: PermissionRequest.() -> Unit) =
    AndPermission
        .with(this)
        .runtime()
        .permission(permissions)
        .apply {
            block()
        }
        .start()