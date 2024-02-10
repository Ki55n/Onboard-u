package com.example.wifimodule.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    var loading = MutableLiveData(false)

}