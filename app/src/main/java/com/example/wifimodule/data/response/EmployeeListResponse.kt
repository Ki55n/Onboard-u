package com.example.wifimodule.data.response

import com.example.wifimodule.data.common.EmployeeDetails

data class EmployeeListResponse(
    val data: List<EmployeeDetails>? = null,
)

