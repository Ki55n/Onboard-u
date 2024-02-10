package com.example.wifimodule.presentation.profile

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.response.ProfileResponse
import com.example.wifimodule.data.response.UpdateProfileResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFragVM @Inject constructor(val repository: BackendRepository) :
    BaseViewModel() {
    fun updateProfile(
        name: String,
        storeName: String,
        address: String,
        emailAddress: String,
        phoneNumber: String,
        shippingMethod: String,
        action: Int
        , onResult: (response: Resource<UpdateProfileResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.updateProfile(
                name,
                storeName,
                address,
                emailAddress,
                phoneNumber,
                shippingMethod,
                action
            ).collect { result ->
                onResult.invoke(result)
            }
        }
    }

    fun getProfile(onResult: (response: Resource<ProfileResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.getProfile().collect { result ->
                onResult.invoke(result)
            }
        }
    }
}