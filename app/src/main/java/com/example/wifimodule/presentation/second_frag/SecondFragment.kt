package com.example.wifimodule.presentation.second_frag

import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : BaseFragment<FragmentSecondBinding, SecondFragVM>() {

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {

        }
    }

}