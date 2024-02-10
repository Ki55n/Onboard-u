package com.example.wifimodule.presentation.first_frag

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.common.EmployeeDetails
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstFragVM @Inject constructor(
    private val repository: BackendRepository
) : BaseViewModel() {

    /**
     * LiveData representing the state of address addition response.
     */
    var employeeListResponse = MutableLiveData<Resource<List<EmployeeDetails>>>()
    var employeeList = MutableLiveData<List<EmployeeDetails>>()

    fun getEmployeeList() {
//        viewModelScope.launch {
//            repository.get().collect { result ->
//                employeeListResponse.value = result
//            }
//        }
    }

}