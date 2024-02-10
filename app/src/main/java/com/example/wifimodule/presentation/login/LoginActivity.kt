package com.example.wifimodule.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.wifimodule.R
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.common.SharedPrefs
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.data.response.LoginResponse
import com.example.wifimodule.data.response.UserDetailResponse
import com.example.wifimodule.databinding.ActivityLoginBinding
import com.example.wifimodule.domain.getUserDetail
import com.example.wifimodule.presentation.dashboard.DashboardActivity
import com.example.wifimodule.presentation.enter_email.EnterEmailActivity
import com.example.wifimodule.presentation.upload_gst.UploadGstActivity
import com.example.wifimodule.utils.LocaleHelper
import com.example.wifimodule.utils.isEmail
import com.example.wifimodule.utils.simpleAlert
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginActivityVM>() {

    @Inject
    lateinit var prefs: SharedPrefs
    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            if (prefs.getLanguage()?.isEmpty() == true){
                txtLanguage.text = "hi"
            }else{
                if (prefs.getLanguage().equals("hi")){
                    txtLanguage.text = "en"
                }else{
                    txtLanguage.text = "hi"
                }

            }
            btnLogin.setOnClickListener {
                callLoginAPI()
            }
            txtSignUp.setOnClickListener {
                startActivity(
                    Intent(
                        this@LoginActivity,
                        EnterEmailActivity::class.java
                    ).apply {
                        flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    })
            }
            txtLanguage.setOnClickListener {
                if (txtLanguage.text.toString().trim().equals("hi")) {
                    prefs.saveLanguage("hi")
                    txtLanguage.text = "en"
                    recreate()
                }else{
                    prefs.saveLanguage("en")
                    txtLanguage.text = "hi"
                    recreate()
                }
            }
        }
    }

    fun isValid(): Boolean {
        if (binding.edtEmail.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.email_empty))
            return false
        } else if (!binding.edtEmail.text.trim().toString().isEmail()) {
            simpleAlert(getString(R.string.email_valid))
            return false
        } else if (binding.edtPassword.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.password_empty))
            return false
        }
        return true
    }

    private fun callLoginAPI() {
        if (isValid()) {
            prefs.saveToken("")
            viewModel.loginUser(
                binding.edtEmail.text.toString().trim(),
                binding.edtPassword.text.toString().trim(),
                {
                    handleLogin(it)
                })
        }
    }

    private fun handleLogin(it: Resource<LoginResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
//                TODO uncomment when publish
//                prefs.setIsLogin(true)
                it.data?.let { it1 -> prefs.saveLoginResponse(it1) }
                it?.data?.token?.access?.let { it1 -> prefs.saveToken(it1) }
                callUserDetailAPI()
            } else {
                it.data?.message?.let { it1 -> simpleAlert(it1.toString()) }
            }
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

    private fun callUserDetailAPI() {
        getUserDetail(viewModel.viewModelScope, repository = viewModel.repository, {
            handleUserDetails(it)
        })
    }

    private fun handleUserDetails(it: Resource<UserDetailResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
                if (it?.data?.data?.isSeller == true) {
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
                            UploadGstActivity::class.java
                        ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .putExtras(Bundle.EMPTY)
                    )
//                    startActivity(
//                        Intent(
//                            this@LoginActivity,
//                            UploadGstActivity::class.java
//                        ).apply { putExtra("email", binding.edtEmail.text.toString().trim()) })
                }
                loadingDialog(false)
            } else {
                it.data?.message?.let { it1 -> simpleAlert(it1.toString()) }
            }
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

}