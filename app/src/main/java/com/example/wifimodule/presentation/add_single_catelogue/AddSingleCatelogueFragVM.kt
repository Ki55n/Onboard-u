package com.example.wifimodule.presentation.add_single_catelogue

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.request.CreateProductRequest
import com.example.wifimodule.data.response.ProductCreateResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSingleCatelogueFragVM @Inject constructor(val repository: BackendRepository) : BaseViewModel() {
    fun createProduct(data: CreateProductRequest, onResult: (response: Resource<ProductCreateResponse>?) -> Unit) {
        viewModelScope.launch {
            repository.createProduct(data).collect { result ->
                onResult.invoke(result)
            }
        }
    }

}