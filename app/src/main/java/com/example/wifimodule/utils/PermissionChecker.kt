package com.example.wifimodule.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.wifimodule.base.BaseApp

interface PermissionCallBack {
    fun permissionGranted()
    fun permissionDenied()

    /**
     * Callback on permission "Never show again" checked and denied
     * */
    fun onPermissionDisabled()
}

fun Activity.checkPermissionRationale(permissions: Array<out String>): Boolean {
    var result = true
    for (permission in permissions) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            result = false
            break
        }
    }
    return result
}

fun Context.checkSelfPermissions(permissions: ArrayList<String>): Boolean {
    return permissions.none {
        ContextCompat.checkSelfPermission(
            this.applicationContext,
            it
        ) != PackageManager.PERMISSION_GRANTED
    }
}

fun Activity.requestAllPermissions(permissions: ArrayList<out String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissions.toTypedArray(), requestCode)
}

fun Fragment.checkSelfPermissions(permissions: ArrayList<String>): Boolean {
    return permissions.none {
        BaseApp.INSTANCE?.let { it1 ->
            ContextCompat.checkSelfPermission(
                it1,
                it
            )
        } != PackageManager.PERMISSION_GRANTED
    }
}

fun Fragment.requestAllPermissions(permissions: ArrayList<out String>, requestCode: Int) {
    requestPermissions(permissions.toTypedArray(), requestCode)
}

fun Fragment.checkPermissionRationale(permissions: Array<out String>): Boolean {
    var result = true
    for (permission in permissions) {
        if (!shouldShowRequestPermissionRationale(permission)) {
            result = false
            break
        }
    }
    return result
}
