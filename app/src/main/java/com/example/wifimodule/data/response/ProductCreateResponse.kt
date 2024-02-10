package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class ProductCreateResponse(
    @SerializedName(value = "message", alternate = ["messages"])
    var message: String? = null,
    @SerializedName("status")
    var status: Status? = null
)