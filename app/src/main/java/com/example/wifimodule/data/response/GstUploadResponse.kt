package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class GstUploadResponse(
    @SerializedName(value= "message", alternate = ["messages"])
    var message: String? = null,
    @SerializedName("status")
    var status: Status? = null
) {
    data class Status(
        @SerializedName("code")
        var code: Int? = null,
        @SerializedName("msg")
        var msg: String? = null
    )
}