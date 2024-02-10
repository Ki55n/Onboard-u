package com.example.wifimodule.domain.repository.ml

import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.common.EmployeeDetails
import kotlinx.coroutines.flow.Flow

interface MLRepository {
    suspend fun getEmployeeList(): Flow<Resource<List<EmployeeDetails>>>
}