package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("data")
    var `data`: UserData? = null,
    @SerializedName(value= "message", alternate = ["messages"])
    var message: Any? =null,
    @SerializedName("status")
    var status: Status? = null
)

data class UserData(
    @SerializedName("contact_number")
    var contactNumber: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("email_verified")
    var emailVerified: Boolean = false,
    @SerializedName("is_active")
    var isActive: Boolean = false,
    @SerializedName("is_seller")
    var isSeller: Boolean = false,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("number_verified")
    var numberVerified: Boolean = false
)

