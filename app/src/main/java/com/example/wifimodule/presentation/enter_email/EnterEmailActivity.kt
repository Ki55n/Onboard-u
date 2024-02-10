package com.example.wifimodule.presentation.enter_email

import android.content.Intent
import com.example.wifimodule.R
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.common.SharedPrefs
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.data.response.LoginResponse
import com.example.wifimodule.databinding.ActivityEnterEmailBinding
import com.example.wifimodule.presentation.confirm_otp.ConfirmOtpActivity
import com.example.wifimodule.presentation.login.LoginActivity
import com.example.wifimodule.utils.isEmail
import com.example.wifimodule.utils.simpleAlert
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EnterEmailActivity : BaseActivity<ActivityEnterEmailBinding, EnterEmailActivityVM>() {
    @Inject
    lateinit var prefs: SharedPrefs
    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
        fixViews()
            btnSignup.setOnClickListener {
                callRegisterAPI()
            }
            txtLogin.setOnClickListener {
                startActivity(
                    Intent(
                        this@EnterEmailActivity,
                        LoginActivity::class.java
                    ).apply {
                        flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    })
            }
        }
    }
    
    private fun fixViews(){
    
    }

    private fun callRegisterAPI() {
        if (isValid()) {
            viewModel.registerUser(
                binding.edtEmail.text.toString().trim(),
                binding.edtPassword.text.toString().trim(),
                binding.edtContact.text.toString().trim(),
                binding.edtName.text.toString().trim(),
                {
                    handleRegister(it)
                })
        }
    }

    private fun handleRegister(it: Resource<LoginResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
              it.data?.let { it1 -> prefs.saveLoginResponse(it1) }
                it?.data?.token?.access?.let { it1 -> prefs.saveToken(it1) }
                startActivity(
                    Intent(
                        this@EnterEmailActivity,
                        ConfirmOtpActivity::class.java
                    ).apply { putExtra("email", binding.edtEmail.text.toString().trim()) })
            }else{
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
        if (binding.edtName.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.name_empty))
            return false
        } else if (binding.edtEmail.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.email_empty))
            return false
        } else if (!binding.edtEmail.text.trim().toString().isEmail()) {
            simpleAlert(getString(R.string.email_valid))
            return false
        } else if (binding.edtPassword.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.password_empty))
            return false
        } else if (binding.edtPassword.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.confirm_password_empty))
            return false
        } else if (!binding.edtPassword.text.trim().equals(binding.edtPassword.text.trim())) {
            simpleAlert(getString(R.string.confirm_password_equal))
            return false
        }
        return true
    }

}
