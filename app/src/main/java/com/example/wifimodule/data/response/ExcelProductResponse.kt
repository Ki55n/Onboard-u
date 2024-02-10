package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class ExcelProductResponse(
    @SerializedName("data")
    var `data`: ArrayList<Product?>? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Status? = null
)