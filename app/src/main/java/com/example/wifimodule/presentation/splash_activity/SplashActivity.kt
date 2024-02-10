package com.example.wifimodule.presentation.splash_activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import com.example.wifimodule.HomeScreenActivity
import com.example.wifimodule.base.common.SharedPrefs
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.databinding.ActivitySplashBinding
import com.example.wifimodule.presentation.dashboard.DashboardActivity
import com.example.wifimodule.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityVM>() {
    @Inject
    lateinit var pref: SharedPrefs

    override fun observeViewModel() {

    }


    override fun initViewBinding() {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            if (pref.getIsLogin()) {
                startActivity(
                    Intent(
                        this,
                        DashboardActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .putExtras(Bundle.EMPTY)
                )
            } else {
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .putExtras(Bundle.EMPTY)
                )
            }

        }, 2000)
    }

}