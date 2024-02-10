package com.example.wifimodule.data.response

data class UpdateProfileResponse(
    val name: String?,
    val store_name: String?,
    val address: Address?,
    val email_address: String?,
    val phone_number: String?,
    val shipping_method: String?
)

data class Address(
    val street: String?,
    val city: String?,
    val state: String?,
    val postal_code: String?,
    val country: String?
)

data class ResponseStatus(
    val code: Int,
    val msg: String
)

data class ProfileResponse(
    val data: Data?,
    val message: String,
    val status: ResponseStatus
)

data class Data(
    val name: String?,
    val store_name: String,
    val address: String?,
    val email_address: String?,
    val phone_number: String?,
    val shipping_method: String?,
    val bank_details: List<String>
)
