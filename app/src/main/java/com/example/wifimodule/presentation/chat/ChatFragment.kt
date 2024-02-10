package com.example.wifimodule.presentation.chat

import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.wifimodule.R
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.data.response.ProfileResponse
import com.example.wifimodule.data.response.UpdateProfileResponse
import com.example.wifimodule.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding, ChatFragVM>() {

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonSend = view.findViewById<Button>(R.id.buttonSend)
        val  textViewResponse = view.findViewById<TextView>(R.id.textViewResponse)
        val editTextQuestion = view.findViewById<EditText>(R.id.editTextQuestion)
        super.onViewCreated(view, savedInstanceState)

        buttonSend.setOnClickListener {
            val question = editTextQuestion.text.toString()
            if (question.isNotEmpty()) {
                // Llamada al método para obtener la respuesta del chatbot
                getChatbotResponse(question, buttonSend, textViewResponse)
            }
        }
    }

    private fun getChatbotResponse(question: String, buttonSend: Button, textViewResponse: TextView) {

        viewModel.getChatMsg(question) { result ->
            when (result) {
                is Resource.Loading -> {
                    buttonSend.isEnabled = false
                }
                is Resource.Success -> {
                    buttonSend.isEnabled = true
                    textViewResponse.text = question + "\n" + result.data?.response
                }
                is Resource.Error -> {
                    buttonSend.isEnabled = true
                    textViewResponse.text = result.message
                }

                else -> {}
            }
        }

    }
}
