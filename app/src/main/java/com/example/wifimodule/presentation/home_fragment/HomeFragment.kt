package com.example.wifimodule.presentation.home_fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.viewModelScope
import com.example.wifimodule.R
import com.example.wifimodule.base.BaseApp
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.data.response.CategoryData
import com.example.wifimodule.data.response.CategoryResponse
import com.example.wifimodule.data.response.CatelogueData
import com.example.wifimodule.data.response.CatelogueResponse
import com.example.wifimodule.data.response.Product
import com.example.wifimodule.data.response.ProductResonse
import com.example.wifimodule.data.response.UserDetailResponse
import com.example.wifimodule.databinding.CustomviewListviewCatalogueBinding
import com.example.wifimodule.databinding.FragmentHomeBinding
import com.example.wifimodule.domain.getCategory
import com.example.wifimodule.domain.getCatelogue
import com.example.wifimodule.domain.getProduct
import com.example.wifimodule.domain.getUserDetail
import com.example.wifimodule.presentation.create_multiple_catelogue.CreateMultipleCatelogueActivity
import com.example.wifimodule.presentation.create_single_catelogue.CreateSingleCatelogueActivity
import com.example.wifimodule.utils.recyclerview.RecyclerViewLayoutManager
import com.example.wifimodule.utils.recyclerview.RecyclerViewLinearLayout
import com.example.wifimodule.utils.recyclerview.setUpRecyclerView_Binding
import com.example.wifimodule.utils.simpleAlert
import com.example.wifimodule.utils.toIntLocal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragVM>() {

    override val toolbar: Toolbar?
        get() = super.toolbar


    override fun observeViewModel() {
        viewModel.apply {
            getCatelogue(this.viewModelScope, viewModel.repository, {
                handleCatelogue(it)
            })
            getCategory(this.viewModelScope, viewModel.repository, {
                handleCategory(it)
            })
            getUserDetail(this.viewModelScope, repository, {
                handleUseDetail(it)
            })
            getProduct(this.viewModelScope, repository = viewModel.repository, onResult = {
                handleProductsList(it)
            })
        }
    }

    override fun initViewBinding() {
        binding.apply {
            llCreateCatelogue.setOnClickListener {
                startActivity(
                    Intent(
                        requireActivity(),
                        CreateSingleCatelogueActivity::class.java
                    )
                )
//                findNavController().navigate(R.id.action_create_single_catelogue)

            }
            addMultipleCatalogueBtn.setOnClickListener {
                startActivity(
                    Intent(
                        requireActivity(),
                        CreateMultipleCatelogueActivity::class.java
                    ).apply { Bundle.EMPTY }
                )
            }
        }
    }

    private fun handleUseDetail(it: Resource<UserDetailResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
                binding.txtUserName.text = it.data?.data?.name
            } else {
                it.data?.message?.let { it1 -> requireActivity().simpleAlert(it1.toString()) }
            }
            loadingDialog(false)
        }
        if (it is Resource.Loading) {
            loadingDialog(true)
        }
        if (it is Resource.Error) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.APIException) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.Idle) {

        }
    }

    private fun handleCategory(it: Resource<CategoryResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
                it.data.data?.let { it1 -> setCategorySpinner(it1) }
            } else {
                it.data?.message?.let { it1 -> requireActivity().simpleAlert(it1.toString()) }
            }
            loadingDialog(false)
        }
        if (it is Resource.Loading) {
            loadingDialog(true)
        }
        if (it is Resource.Error) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.APIException) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.Idle) {

        }
    }

    private fun handleProductsList(it: Resource<ProductResonse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
//                it.data.data?.let { it1 -> BaseApp.INSTANCE.productsList = it1 }
            } else {
                it.data?.message?.let { it1 -> requireActivity().simpleAlert(it1.toString()) }
            }
            loadingDialog(false)
        }
        if (it is Resource.Loading) {
            loadingDialog(true)
        }
        if (it is Resource.Error) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.APIException) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.Idle) {

        }
    }

    private fun setCategorySpinner(data: ArrayList<CategoryData?>) {
        binding.spinnerCategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.setAdapter(aa)
    }

    private fun handleCatelogue(it: Resource<CatelogueResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
                it.data.data?.let { it1 -> setupRecyclerview(it1) }
            } else {
                it.data?.message?.let { it1 -> requireActivity().simpleAlert(it1.toString()) }
            }
            loadingDialog(false)
        }
        if (it is Resource.Loading) {
            loadingDialog(true)
        }
        if (it is Resource.Error) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.APIException) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.Idle) {

        }
    }

    private fun setupRecyclerview(data: ArrayList<CatelogueData?>) {
        binding.rvCatelogue.setUpRecyclerView_Binding<CatelogueData, CustomviewListviewCatalogueBinding>(
            R.layout.customview_listview_catalogue,
            data,
            RecyclerViewLayoutManager.LINEAR,
            RecyclerViewLinearLayout.VERTICAL
        ) {
            contentBinder { item, binding, position ->
                binding.txtTitle.text = item.name
                binding.linear1.setOnClickListener {
                    callProductAPI(item)
                }
            }
            isNestedScrollingEnabled = false
        }
    }

    private fun callProductAPI(name: CatelogueData) {
        getProduct(viewModelScope = viewModel.viewModelScope, repository = viewModel.repository) {
            handleProduct(it, name)
        }
    }

    private fun handleProduct(it: Resource<ProductResonse>?, name: CatelogueData) {
        if (it is Resource.Success) {
            var productList = ArrayList<Product?>()
            var filterData =
                it.data?.data?.filter { productData -> productData?.product?.catalogue?.equals(name.id.toString()) == true }
            filterData?.forEach { productData ->
                productData?.product?.discount = productData?.discount?.toDouble()
                productData?.product?.finalPrice = productData?.finalPrice?.toInt()
                productData?.product?.stock = productData?.stock?.toIntLocal()
                productList.add(productData?.product)
            }
            BaseApp.INSTANCE.productsList = productList
            startActivity(
                Intent(
                    requireContext(),
                    CreateMultipleCatelogueActivity::class.java
                ).apply {
                    if (productList?.isNotEmpty() == true) putExtra("isFrom", "Catalogue")
                    putExtra("catalogueName", name.name)
                    putExtra("catelogueId", name.id.toString())
                })
            loadingDialog(false)
        }
        if (it is Resource.Loading) {
            loadingDialog(true)
        }
        if (it is Resource.Error) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.APIException) {
            loadingDialog(false)
            it.message?.let { it1 -> requireActivity().simpleAlert(it1) }
        }
        if (it is Resource.Idle) {

        }
    }


}