package com.example.wifimodule.presentation.upload_gst

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.response.GstUploadResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class UploadGstActivityVM @Inject constructor(val repository: BackendRepository) : BaseViewModel() {
    fun uploadGstCertificate(gstCertificate: MultipartBody.Part?, onResult: (response: Resource<GstUploadResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.uploadGstCertificate(gstCertificate).collect { result ->
                onResult.invoke(result)
            }
        }
    }

}