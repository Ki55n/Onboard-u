package com.example.wifimodule.presentation.dashboard

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.wifimodule.R
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.databinding.ActivityDashboardBinding
import com.example.wifimodule.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardActivityVM>() {

    override val toolbar: Toolbar?
        get() = binding.toolBar.toolbar

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navHostFragment?.navController?.let {
            NavigationUI.setupWithNavController(
                binding.bottomBar,
                it
            )
        }
        binding.apply {
//            toolBar.backImgBtn.isGone = false
            toolBar.backImgBtn.setImageResource(R.mipmap.ic_launcher)
            toolBar.backImgBtn.layoutParams.apply { width =MATCH_PARENT }
            toolBar.micImgBtn.setPadding(resources.getDimensionPixelSize(R.dimen._1sdp))
            toolBar.titleTxt.isGone = true
            toolBar.welcomeTitleLayout.isVisible = false
//            toolBar.welcomeTitleTxt.text = "SatyaK!"
            toolBar.micImgBtn.setImageResource(R.drawable.menu)
            toolBar.micImgBtn.setPadding(resources.getDimensionPixelSize(R.dimen._14sdp))
        }
    }
}