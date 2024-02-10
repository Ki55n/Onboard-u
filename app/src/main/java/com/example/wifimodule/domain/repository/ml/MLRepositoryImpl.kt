package com.example.wifimodule.domain.repository.ml

import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.common.makeApiCall
import com.example.wifimodule.data.common.EmployeeDetails
import com.example.wifimodule.data.remote_service.MLService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MLRepositoryImpl @Inject constructor(private val api: MLService) :
    MLRepository {

    override suspend fun getEmployeeList(): Flow<Resource<List<EmployeeDetails>>> =
        flow {
            emit(Resource.Loading)
            val result = makeApiCall { api.getEmployeeList() }
            emit(result)
        }.flowOn(Dispatchers.IO)

}