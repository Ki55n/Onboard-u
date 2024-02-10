package com.example.wifimodule.presentation.enter_email

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.response.LoginResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterEmailActivityVM @Inject constructor(private val repository: BackendRepository    ) : BaseViewModel() {
    fun registerUser(
        email: String,
        password: String,
        contactNumber: String,
        name:String,
        onResult: (response: Resource<LoginResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.registerUser(email, password,contactNumber, name).collect { result ->
                onResult.invoke(result)
            }
        }
    }

}