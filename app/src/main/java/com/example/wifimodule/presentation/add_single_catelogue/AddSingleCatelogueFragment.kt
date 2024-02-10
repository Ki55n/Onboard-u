package com.example.wifimodule.presentation.add_single_catelogue

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.wifimodule.R
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.data.request.CreateProductRequest
import com.example.wifimodule.data.response.CategoryData
import com.example.wifimodule.data.response.CategoryResponse
import com.example.wifimodule.data.response.CatelogueData
import com.example.wifimodule.data.response.CatelogueResponse
import com.example.wifimodule.data.response.Product
import com.example.wifimodule.data.response.ProductCreateResponse
import com.example.wifimodule.data.response.ProductData
import com.example.wifimodule.databinding.FragmentAddSingleCatelogueBinding
import com.example.wifimodule.domain.getCategory
import com.example.wifimodule.domain.getCatelogue
import com.example.wifimodule.presentation.success_catelog.SuccessCatelogueFragment
import com.example.wifimodule.utils.simpleAlert
import com.example.wifimodule.utils.toIntLocal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSingleCatelogueFragment :
    BaseFragment<FragmentAddSingleCatelogueBinding, AddSingleCatelogueFragVM>() {
    var selectedCategory: CategoryData? = null
    var selectedCatalogue: CatelogueData? = null

    override val titleToolbar: String?
        get() = "add Catelogue"

    private val productsList = ArrayList<ProductData?>()

    override fun observeViewModel() {
        viewModel.apply {
            getCategory(this.viewModelScope, viewModel.repository, {
                handleCategory(it)
            })
            getCatelogue(this.viewModelScope, viewModel.repository) {
                handleCatalogue(it)
            }
        }
    }

    override fun initViewBinding() {
        binding.apply {
            product.btnSubmit.setOnClickListener {
                if (isValid()) {
                    val productData = Product()
                    productData.name =
                        binding.product.edtProductName.text.toString().trim()
                    productData.description =
                        binding.product.edtProductDescription.text.toString().trim()
                    productData.features =
                        binding.product.edtProductFeatures.text.toString().trim()
                    productData.price =
                        binding.product.edtPrice.text.toString().trim()?.toIntLocal()
                    productData.discount =
                        binding.product.edtDiscountPer?.text?.toString()?.toIntLocal()?.toDouble()
                    productData.finalPrice =
                        binding.product.edtReceivePrice.text.toString().trim()?.toIntLocal()
                    productData.stock =
                        binding.product.edtProductStock.text.toString().trim()?.toIntLocal()
                    productData.category = selectedCategory?.name
                    productData.catalogue = selectedCatalogue?.name
                    val data = CreateProductRequest(arrayListOf(productData))
                    insertProducts(data)
                }

            }
        }
    }

    private fun insertProducts(request: CreateProductRequest) {
        viewModel.createProduct(request) {
            handleProductCreation(it)
        }
    }

    private fun handleProductCreation(it: Resource<ProductCreateResponse>?) {
        if (it is Resource.Success) {
            loadingDialog(false)
            if (it?.data?.status?.code?.equals("200") == true) {
                findNavController().navigate(R.id.catelogueSuccess)
//                startActivity(Intent(requireContext(), SuccessCatelogueFragment::class.java))
            } else {
                it.data?.message?.let { it1 -> requireContext().simpleAlert(it1) }
            }
        }
        if (it is Resource.Loading) {
            loadingDialog(true)
        }
        if (it is Resource.Error) {
            loadingDialog(false)
            it.message?.let { it1 -> requireContext().simpleAlert(it1) }
        }
        if (it is Resource.APIException) {
            loadingDialog(false)
            it.message?.let { it1 -> requireContext().simpleAlert(it1) }
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

    private fun handleCatalogue(it: Resource<CatelogueResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
                it.data.data?.let { it1 -> setCatalogueSpinner(it1) }
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
        binding.product.spnCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    selectedCategory = data.get(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.product.spnCategory.setAdapter(aa)
    }

    private fun setCatalogueSpinner(data: ArrayList<CatelogueData?>) {
        binding.product.spnCatalogue.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    selectedCatalogue = data.get(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.product.spnCatalogue.setAdapter(aa)
    }

    fun isValid(): Boolean {
        if (binding.product.edtProductName.text.trim().isEmpty()) {
            requireContext().simpleAlert(getString(R.string.product_name_empty))
            return false
        } else if (binding.product.edtPrice.text.trim().isEmpty()) {
            requireContext().simpleAlert(getString(R.string.product_price_empty))
            return false
        }
        return true
    }

}