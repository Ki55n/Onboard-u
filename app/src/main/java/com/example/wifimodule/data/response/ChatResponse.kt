package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("response")
    var response: String? = null
)