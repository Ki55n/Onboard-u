package com.example.wifimodule.presentation.create_multiple_catelogue

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.wifimodule.R
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.databinding.ActivityCreateMultipleCatelogueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateMultipleCatelogueActivity :
    BaseActivity<ActivityCreateMultipleCatelogueBinding, CreateMultipleCatelogueActivityVM>() {
    override val toolbar: Toolbar?
        get() = binding.toolBar.toolbar

    override fun observeViewModel() {
        if(intent.hasExtra("isFrom")){
            val navHostFragment =
                supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment?
            val graph =
                navHostFragment?.navController?.navInflater?.inflate(R.navigation.multiple_catalogue_graph)
            graph?.setStartDestination(R.id.addMultipleCatalogueFragment)
            graph?.let { navHostFragment.navController.setGraph(it, intent.extras?:Bundle.EMPTY) }
        }
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            binding.toolBar.backImgBtn.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            binding.toolBar.titleTxt.text = "Add Multiple Catalogue"
        }
    }
}