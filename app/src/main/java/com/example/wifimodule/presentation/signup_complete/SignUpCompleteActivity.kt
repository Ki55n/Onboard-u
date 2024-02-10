package com.example.wifimodule.presentation.signup_complete

import android.content.Intent
import com.example.wifimodule.HomeScreenActivity
import com.example.wifimodule.base.common.SharedPrefs
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.databinding.ActivitySignUpCompleteBinding
import com.example.wifimodule.presentation.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpCompleteActivity :
    BaseActivity<ActivitySignUpCompleteBinding, SignUpCompleteActivityVM>() {
    @Inject
    lateinit var prefs: SharedPrefs
    override fun observeViewModel() {
        if (intent.hasExtra("seller_id")){
            binding.txtSellerID.text = intent.getStringExtra("seller_id")
        }
        if (intent.hasExtra("seller_name")){
            binding.txtSellerName.text = intent.getStringExtra("seller_name")
        }
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            binding.btnProceed.setOnClickListener {
                prefs.setIsLogin(true)
                startActivity(Intent(this@SignUpCompleteActivity, DashboardActivity::class.java))
            }
        }
    }

}