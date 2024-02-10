package com.example.wifimodule.presentation.home_fragment

import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragVM @Inject constructor(val repository: BackendRepository) : BaseViewModel() {

}