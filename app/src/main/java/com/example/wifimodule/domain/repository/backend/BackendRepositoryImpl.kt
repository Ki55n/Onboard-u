package com.example.wifimodule.domain.repository.backend

import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.common.makeApiCallNormal
import com.example.wifimodule.data.remote_service.Address
import com.example.wifimodule.data.remote_service.BackendService
import com.example.wifimodule.data.request.CreateProductRequest
import com.example.wifimodule.data.request.GstRequest
import com.example.wifimodule.data.response.CategoryResponse
import com.example.wifimodule.data.response.CatelogueResponse
import com.example.wifimodule.data.response.ChatResponse
import com.example.wifimodule.data.response.ExcelProductResponse
import com.example.wifimodule.data.response.Chat
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class BackendRepositoryImpl @Inject constructor(private val api: BackendService) :
    BackendRepository {

    override suspend fun loginUser(email: String, password: String): Flow<Resource<LoginResponse>> =
        flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.userLogin(email, password) }
            emit(result)
        }.flowOn(Dispatchers.IO)

    override suspend fun uploadGstCertificate(gstCertificate: MultipartBody.Part?): Flow<Resource<GstUploadResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.uploadGstCertificate(gstCertificate) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    override suspend fun uploadCsv(gstCertificate: MultipartBody.Part?): Flow<Resource<ExcelProductResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.uploadCsv(gstCertificate) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserDetails(): Flow<Resource<UserDetailResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.getUserDetail() }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCategory(): Flow<Resource<CategoryResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.getCategory() }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun registerUser(
        email: String,
        password: String,
        contactNumber: String,
        name: String
    ): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Loading)
            val result =
                makeApiCallNormal { api.registerUser(email, password, contactNumber, name) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun verifyOTP(otp: String, of: String): Flow<Resource<OTPResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.verifyOTP(otp, of) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun verifyGst(
        request: GstRequest
    ): Flow<Resource<VerifyGstResponse>> {
        return flow {
            emit(Resource.Loading)
            val result =
                makeApiCallNormal { api.verifyGst(request) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getProduct(pageSize: Int?, page: Int?): Flow<Resource<ProductResonse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.getProduct(pageSize, page) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCatelogue(): Flow<Resource<CatelogueResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.getCatelogue() }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun createProduct(data: CreateProductRequest): Flow<Resource<ProductCreateResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.createProduct(data) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateProfile(
        name: String,
        storeName: String,
        address: String,
        emailAddress: String,
        phoneNumber: String,
        shippingMethod: String,
        action: Int
    ): Flow<Resource<UpdateProfileResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal {
                api.updateProfile(
                    name,
                    storeName,
                    Address(street = address),
                    emailAddress,
                    phoneNumber,
                    shippingMethod,
                    action
                )
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getProfile(): Flow<Resource<ProfileResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.getProfile() }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getChat(msg: String): Flow<Resource<Chat>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.getChat(url = "https://chat-ph26smzt3a-uc.a.run.app/get",msg) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun uploadProductImage(productImage: MultipartBody.Part?): Flow<Resource<ImageUploadResponse>> {
        return flow {
            emit(Resource.Loading)
            val result = makeApiCallNormal { api.uploadProductImage(productImage) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}