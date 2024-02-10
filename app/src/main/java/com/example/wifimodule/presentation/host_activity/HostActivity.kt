package com.example.wifimodule.presentation.host_activity

import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : BaseActivity<ActivityHostBinding, HostActivityVM>() {

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {

        }
    }

}