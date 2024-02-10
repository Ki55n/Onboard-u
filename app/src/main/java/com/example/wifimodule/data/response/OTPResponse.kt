package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class OTPResponse(
    @SerializedName("message")
    var message: String = "",
    @SerializedName("status")
    var status: Status = Status()
)