package com.example.wifimodule.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.wifimodule.data.response.Product
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    var productsList = ArrayList<Product?>()
    companion object {
        lateinit var INSTANCE: BaseApp

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}