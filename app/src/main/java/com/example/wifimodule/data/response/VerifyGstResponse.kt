package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class VerifyGstResponse(
    @SerializedName("data")
    var `data`: GstData? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Status? = null
)

data class GstData(
    @SerializedName("business_address")
    var businessAddress: String? = null,
    @SerializedName("certificate")
    var certificate: String? = null,
    @SerializedName("gst_number")
    var gstNumber: String? = null,
    @SerializedName("gst_type")
    var gstType: String? = null,
    @SerializedName("gst_verified")
    var gstVerified: Boolean? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("legal_name")
    var legalName: String? = null,
    @SerializedName("seller_id")
    var sellerId: String? = null,
    @SerializedName("trade_name")
    var tradeName: String? = null
)
