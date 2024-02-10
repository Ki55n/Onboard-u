package com.example.wifimodule.presentation.create_single_catelogue

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.wifimodule.R
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.databinding.ActivityCreateMultipleCatelogueBinding
import com.example.wifimodule.databinding.ActivityCreateSingleCatelogueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateSingleCatelogueActivity : BaseActivity<ActivityCreateSingleCatelogueBinding, CreateSingleCatelogueActivityVM>() {
    override val toolbar: Toolbar?
        get() = binding.toolBar.toolbar

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            binding.toolBar.backImgBtn.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            binding.toolBar.titleTxt.text = "Add Single Catalogue"
        }
    }
}