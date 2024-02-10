package com.example.wifimodule.data.request


import com.google.gson.annotations.SerializedName

data class GstRequest(
    @SerializedName("business_address")
    var businessAddress: String? = null,
    @SerializedName("gst-no")
    var gstNo: String? = null,
    @SerializedName("gst-type")
    var gstType: String? = null,
    @SerializedName("legal-name")
    var legalName: String? = null,
    @SerializedName("trade-name")
    var tradeName: String? = null
)