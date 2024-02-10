package com.example.wifimodule.domain

import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.request.CreateProductRequest
import com.example.wifimodule.data.response.CategoryResponse
import com.example.wifimodule.data.response.CatelogueResponse
import com.example.wifimodule.data.response.ProductCreateResponse
import com.example.wifimodule.data.response.ProductResonse
import com.example.wifimodule.data.response.UserDetailResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun getUserDetail(
    viewModelScope: CoroutineScope,
    repository: BackendRepository,
    onResult: (response: Resource<UserDetailResponse>?) -> Unit
) {
    viewModelScope.launch {
        repository.getUserDetails().collect { result ->
            onResult.invoke(result)
        }
    }
}

fun getCategory(
    viewModelScope: CoroutineScope,
    repository: BackendRepository,
    onResult: (response: Resource<CategoryResponse>?) -> Unit
) {
    viewModelScope.launch {
        repository.getCategory().collect { result ->
            onResult.invoke(result)
        }
    }
}

fun getProduct(
    viewModelScope: CoroutineScope,
    pageSize: Int? = 10000,
    page: Int? = 1,
    repository: BackendRepository,
    onResult: (response: Resource<ProductResonse>?) -> Unit
) {
    viewModelScope.launch {
        repository.getProduct(pageSize, page).collect { result ->
            onResult.invoke(result)
        }
    }
}

fun getCatelogue(
    viewModelScope: CoroutineScope,
    repository: BackendRepository,
    onResult: (response: Resource<CatelogueResponse>?) -> Unit
) {
    viewModelScope.launch {
        repository.getCatelogue().collect { result ->
            onResult.invoke(result)
        }
    }
}

fun createProduct(
    viewModelScope: CoroutineScope,
    repository: BackendRepository,
    data: CreateProductRequest,
    onResult: (response: Resource<ProductCreateResponse>?) -> Unit
) {
    viewModelScope.launch {
        repository.createProduct(data).collect { result ->
            onResult.invoke(result)
        }
    }
}