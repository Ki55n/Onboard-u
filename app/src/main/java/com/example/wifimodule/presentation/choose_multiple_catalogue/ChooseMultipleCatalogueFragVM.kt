package com.example.wifimodule.presentation.choose_multiple_catalogue

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.response.ExcelProductResponse
import com.example.wifimodule.data.response.GstUploadResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ChooseMultipleCatalogueFragVM @Inject constructor(val repository: BackendRepository) : BaseViewModel() {
    fun uploadCatelogCSV(gstCertificate: MultipartBody.Part?, onResult: (response: Resource<ExcelProductResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.uploadCsv(gstCertificate).collect { result ->
                onResult.invoke(result)
            }
        }
    }


}