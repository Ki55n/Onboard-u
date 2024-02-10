package com.example.wifimodule.data.response


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    var `data`: ArrayList<CategoryData?>? = null,
    @SerializedName(value = "message", alternate = ["messages"])
    var message: String? = null,
    @SerializedName("status")
    var status: Status? = null
)

data class CategoryData(
    @SerializedName("created_by")
    var createdBy: Int? = null,
    @SerializedName("created_on")
    var createdOn: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("updated_on")
    var updatedOn: String? = null
){
    override fun toString(): String {
        return name?:""
    }
}
