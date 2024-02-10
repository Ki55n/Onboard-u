package com.example.wifimodule.presentation.chat

import androidx.lifecycle.viewModelScope
import com.example.wifimodule.base.BaseViewModel
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.data.response.Chat
import com.example.wifimodule.data.response.ProfileResponse
import com.example.wifimodule.domain.repository.backend.BackendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ChatFragVM @Inject constructor(val repository: BackendRepository) :
    BaseViewModel() {


    fun getChatMsg(msg: String, onResult: (response: Resource<Chat>?) -> Unit) {
        viewModelScope.launch {
            repository.getChat(msg).collect({ result ->
                onResult.invoke(result)
            })
        }
    }
}