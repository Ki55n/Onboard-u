package com.example.wifimodule.data.remote_service

import com.example.wifimodule.data.WrappedResponse
import com.example.wifimodule.data.common.EmployeeDetails
import retrofit2.Response
import retrofit2.http.GET

/**
 * Employee management service interface.
 *
 * This interface defines the methods for managing data in the application.
 */
interface MLService {

    /**
     * Retrieves the available employees list
     *
     * @return A Response object that wraps the employee list in response data.
     */
    @GET("employees")
    suspend fun getEmployeeList(): Response<WrappedResponse<List<EmployeeDetails>>>

}