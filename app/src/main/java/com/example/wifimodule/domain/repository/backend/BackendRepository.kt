package com.example.wifimodule.domain.repository.backend

import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.request.CreateProductRequest
import com.example.wifimodule.data.request.GstRequest
import com.example.wifimodule.data.response.CategoryResponse
import com.example.wifimodule.data.response.CatelogueResponse
import com.example.wifimodule.data.response.Chat
import com.example.wifimodule.data.response.ExcelProductResponse
import com.example.wifimodule.data.response.GstUploadResponse
import com.example.wifimodule.data.response.ImageUploadResponse
import com.example.wifimodule.data.response.LoginResponse
import com.example.wifimodule.data.response.OTPResponse
import com.example.wifimodule.data.response.ProductCreateResponse
import com.example.wifimodule.data.response.ProductResonse
import com.example.wifimodule.data.response.ProfileResponse
import com.example.wifimodule.data.response.UpdateProfileResponse
import com.example.wifimodule.data.response.UserDetailResponse
import com.example.wifimodule.data.response.VerifyGstResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface BackendRepository {
    suspend fun loginUser(email: String, password: String): Flow<Resource<LoginResponse>>
    suspend fun uploadGstCertificate(gstCertificate: MultipartBody.Part?): Flow<Resource<GstUploadResponse>>
    suspend fun uploadCsv(gstCertificate: MultipartBody.Part?): Flow<Resource<ExcelProductResponse>>
    suspend fun getUserDetails(): Flow<Resource<UserDetailResponse>>
    suspend fun getCategory(): Flow<Resource<CategoryResponse>>


    suspend fun registerUser(
        email: String,
        password: String,
        contactNumber: String,
        name: String
    ): Flow<Resource<LoginResponse>>

    suspend fun verifyOTP(otp: String, of: String = "email"): Flow<Resource<OTPResponse>>
    suspend fun verifyGst(
        request: GstRequest
    ): Flow<Resource<VerifyGstResponse>>

    suspend fun getProduct(pageSize: Int?, page: Int?): Flow<Resource<ProductResonse>>
    suspend fun getCatelogue(): Flow<Resource<CatelogueResponse>>
    suspend fun createProduct(data: CreateProductRequest): Flow<Resource<ProductCreateResponse>>

    suspend fun updateProfile(
        name: String,
        storeName: String,
        address: String,
        emailAddress: String,
        phoneNumber: String,
        shippingMethod: String,
        action: Int
    ): Flow<Resource<UpdateProfileResponse>>

    suspend fun getProfile(): Flow<Resource<ProfileResponse>>
    suspend fun getChat(msg: String): Flow<Resource<Chat>>

    suspend fun uploadProductImage(productImage: MultipartBody.Part?): Flow<Resource<ImageUploadResponse>>
}