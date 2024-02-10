package com.example.wifimodule.data.remote_service

import com.example.wifimodule.data.WrappedResponse
import com.example.wifimodule.data.common.EmployeeDetails
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
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Employee management service interface.
 *
 * This interface defines the methods for managing data in the application.
 */
interface BackendService {

    /**
     * Retrieves the available employees list
     *
     * @return A Response object that wraps the employee list in response data.
     */
    @GET("employees")
    suspend fun getEmployeeList(): Response<WrappedResponse<List<EmployeeDetails>>>

    @FormUrlEncoded
    @POST("api/login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @GET("api/user-details")
    suspend fun getUserDetail(): Response<UserDetailResponse>

    @GET("api/get/categories-lists")
    suspend fun getCategory(): Response<CategoryResponse>

    @GET("api/get/products-lists")
    suspend fun getProduct(
        @Query("page-size") pageSize: Int?,
        @Query("page") page: Int?
    ): Response<ProductResonse>

    @GET("api/get/catalogue-lists")
    suspend fun getCatelogue(): Response<CatelogueResponse>

    @POST("api/create/product")
    suspend fun createProduct(@Body data: CreateProductRequest): Response<ProductCreateResponse>

    @FormUrlEncoded
    @POST("api/register")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("contact_number") contactNumber: String,
        @Field("name") name: String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("api/verify/otp")
    suspend fun verifyOTP(
        @Field("otp") otp: String,
        @Field("of") of: String,
    ): Response<OTPResponse>

    @POST("api/update/gst-details")
    suspend fun verifyGst(
        @Body request: GstRequest
    ): Response<VerifyGstResponse>

    @Multipart
    @POST("api/upload/gst-certificate")
    suspend fun uploadGstCertificate(@Part gstCertificate: MultipartBody.Part?): Response<GstUploadResponse>

    @Multipart
    @POST("api/extract/excel")
    suspend fun uploadCsv(@Part gstCertificate: MultipartBody.Part?): Response<ExcelProductResponse>

    @POST("api/seller-details")
    suspend fun updateProfile(
        @Field("business-name") name: String,
        @Field("store_name") storeName: String,
        @Field("business-address") address: Address,
        @Field("business-email") emailAddress: String,
        @Field("business-contact_number") phoneNumber: String,
        @Field("business-shipping_method") shippingMethod: String,
        @Field("action") action: Int
    ): Response<UpdateProfileResponse>

    @GET("api/business-details")
    suspend fun getProfile(): Response<ProfileResponse>
    @GET()
    suspend fun getChat(@Url url: String,@Query("msg") msg: String): Response<Chat>

    @Multipart
    @POST("api/upload/product-image")
    suspend fun uploadProductImage(@Part productImage: MultipartBody.Part?): Response<ImageUploadResponse>
}

data class Address(
    @SerializedName("street")
    val street: String
)