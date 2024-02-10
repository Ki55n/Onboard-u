package com.example.wifimodule.presentation.success_catelog

import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.databinding.ActivitySuccessCatelogueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessCatelogueFragment :
    BaseFragment<ActivitySuccessCatelogueBinding, SuccessCatelogueFragmentVM>() {

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            bntHome.setOnClickListener {
                requireActivity().finish()
            }
        }
    }

}