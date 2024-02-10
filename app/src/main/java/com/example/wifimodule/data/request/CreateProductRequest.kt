package com.example.wifimodule.data.request


import com.example.wifimodule.data.response.Product
import com.example.wifimodule.data.response.ProductData
import com.google.gson.annotations.SerializedName

data class CreateProductRequest(
    @SerializedName("products")
    var products: ArrayList<Product?>? = null
)