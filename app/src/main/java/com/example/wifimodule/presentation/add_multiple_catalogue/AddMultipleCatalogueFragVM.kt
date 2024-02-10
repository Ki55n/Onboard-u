package com.example.wifimodule.presentation.add_multiple_catalogue

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.request.CreateProductRequest
import com.example.wifimodule.data.response.GstUploadResponse
import com.example.wifimodule.data.response.ImageUploadResponse
import com.example.wifimodule.data.response.LoginResponse
import com.example.wifimodule.data.response.ProductCreateResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class AddMultipleCatalogueFragVM @Inject constructor(val repository: BackendRepository) : BaseViewModel() {

    fun createProduct(data: CreateProductRequest, onResult: (response: Resource<ProductCreateResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.createProduct(data).collect { result ->
                onResult.invoke(result)
            }
        }
    }

    fun uploadProductImage(productImage: MultipartBody.Part?, onResult: (response: Resource<ImageUploadResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.uploadProductImage(productImage).collect { result ->
                onResult.invoke(result)
            }
        }
    }

}