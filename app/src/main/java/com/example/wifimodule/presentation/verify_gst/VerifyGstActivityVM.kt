package com.example.wifimodule.presentation.verify_gst

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.request.GstRequest
import com.example.wifimodule.data.response.VerifyGstResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyGstActivityVM @Inject constructor(val repository: BackendRepository) : BaseViewModel() {
    fun verifyGst(request: GstRequest,onResult: (response: Resource<VerifyGstResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.verifyGst(request).collect { result ->
                onResult.invoke(result)
            }
        }
    }

}