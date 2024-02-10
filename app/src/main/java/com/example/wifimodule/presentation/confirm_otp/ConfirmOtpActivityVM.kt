package com.example.wifimodule.presentation.confirm_otp

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.response.LoginResponse
import com.example.wifimodule.data.response.OTPResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmOtpActivityVM @Inject constructor(private val repository: BackendRepository) : BaseViewModel() {
    fun verifyOTP(
        otp:String,
        of: String = "email",
        onResult: (response: Resource<OTPResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.verifyOTP(otp,of).collect { result ->
                onResult.invoke(result)
            }
        }
    }

}