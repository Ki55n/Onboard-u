package com.example.wifimodule.presentation.confirm_otp

import android.content.Intent
import com.example.wifimodule.R
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.data.response.OTPResponse
import com.example.wifimodule.databinding.ActivityConfirmOtpBinding
import com.example.wifimodule.presentation.login.LoginActivity
import com.example.wifimodule.utils.simpleAlert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmOtpActivity : BaseActivity<ActivityConfirmOtpBinding, ConfirmOtpActivityVM>() {

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            btnConfirmOTP.setOnClickListener {
                callVerifyOtpAPI()
            }
        }
    }

    private fun callVerifyOtpAPI() {
        if (isValid()) {
            viewModel.verifyOTP(
                binding.pinCode.text.toString().trim(),
                onResult = {
                    handleOTP(it)
                })
        }
    }

    private fun handleOTP(it: Resource<OTPResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
                startActivity(Intent(this@ConfirmOtpActivity, LoginActivity::class.java))
            } else {
                it.data?.message?.let { it1 -> simpleAlert(it1.toString()) }
            }
            loadingDialog(false)
        }
        if (it is Resource.Loading) {
            loadingDialog(true)
        }
        if (it is Resource.Error) {
            loadingDialog(false)
            it.message?.let { it1 -> simpleAlert(it1) }
        }
        if (it is Resource.APIException) {
            loadingDialog(false)
            it.message?.let { it1 -> simpleAlert(it1) }
        }
        if (it is Resource.Idle) {

        }
    }

    fun isValid(): Boolean {
        if (binding.pinCode.text?.trim()?.isEmpty() == true) {
            simpleAlert(getString(R.string.pin_empty))
            return false
        }
        return true
    }

}