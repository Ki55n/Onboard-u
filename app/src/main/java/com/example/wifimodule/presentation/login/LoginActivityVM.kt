package com.example.wifimodule.presentation.login

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.common.EmployeeDetails
import com.example.wifimodule.data.response.LoginResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Field
import javax.inject.Inject

@HiltViewModel
class LoginActivityVM @Inject constructor(val repository: BackendRepository) :
    BaseViewModel() {
    fun loginUser(email: String, password: String, onResult: (response: Resource<LoginResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.loginUser(email, password).collect { result ->
                onResult.invoke(result)
            }
        }
    }

}