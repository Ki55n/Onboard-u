package com.example.wifimodule.data.common

import com.google.gson.annotations.SerializedName

data class EmployeeDetails (
    val id: Long? = null,

    @SerializedName("employee_name")
    val employeeName: String? = null,

    @SerializedName("employee_salary")
    val employeeSalary: Long? = null,

    @SerializedName("employee_age")
    val employeeAge: Long? = null,

    @SerializedName("profile_image")
    val profileImage: String? = null
)