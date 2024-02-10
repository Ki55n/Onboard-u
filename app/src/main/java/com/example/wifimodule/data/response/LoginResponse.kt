package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName(value= "message", alternate = ["messages"])
    var message: Any? = null,
    @SerializedName("status")
    var status: Status? = null,
    @SerializedName("token")
    var token: Token? = null
)

data class Status(
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("msg")
    var msg: String? = null
)

data class Token(
    @SerializedName("access")
    var access: String? = null,
    @SerializedName("refresh")
    var refresh: String? = null
)
