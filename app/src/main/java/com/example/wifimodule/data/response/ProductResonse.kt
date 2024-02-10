package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class ProductResonse(
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("data")
    var `data`: ArrayList<ProductData?>? = null,
    @SerializedName(value = "message", alternate = ["messages"])
    var message: String? = null,
    @SerializedName("nex_page")
    var nexPage: Int? = null,
    @SerializedName("pre_page")
    var prePage: Any? = null,
    @SerializedName("status")
    var status: Status? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null
)

data class ProductData(
    @SerializedName("created_on")
    var createdOn: String? = null,
    @SerializedName("discount")
    var discount: Int? = null,
    @SerializedName("final_price")
    var finalPrice: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("is_active")
    var isActive: Boolean? = null,
    @SerializedName("product")
    var product: Product? = null,
    @SerializedName("publish")
    var publish: Boolean? = null,
    @SerializedName("stock")
    var stock: String? = null,
    @SerializedName("user")
    var user: Int? = null
)

data class Product(
    @SerializedName("catalogue")
    var catalogue: String? = "",
    @SerializedName("category")
    var category: String? = "",
    @SerializedName("created_on")
    var createdOn: String? = null,
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("features")
    var features: String? = "",
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("image")
    var image: String? = "",
    @SerializedName("is_active")
    var isActive: Boolean? = null,
    @SerializedName("message")
    var message: Any? = null,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("price")
    var price: Int? = 0,
    @SerializedName("updated_on")
    var updatedOn: String? = null,
    @SerializedName("discount")
    var discount: Double? = 0.0,
    @SerializedName("final_price")
    var finalPrice: Int? = 0,
    @SerializedName("stock")
    var stock: Int? = 0,
)
