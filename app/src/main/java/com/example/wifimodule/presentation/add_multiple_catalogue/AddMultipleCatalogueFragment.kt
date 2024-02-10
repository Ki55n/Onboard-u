package com.example.wifimodule.presentation.add_multiple_catalogue

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.wifimodule.R
import com.example.wifimodule.base.BaseApp
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.common.SharedPrefs
import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.data.request.CreateProductRequest
import com.example.wifimodule.data.response.CategoryData
import com.example.wifimodule.data.response.CategoryResponse
import com.example.wifimodule.data.response.CatelogueData
import com.example.wifimodule.data.response.CatelogueResponse
import com.example.wifimodule.data.response.GstUploadResponse
import com.example.wifimodule.data.response.ImageUploadResponse
import com.example.wifimodule.data.response.Product
import com.example.wifimodule.data.response.ProductCreateResponse
import com.example.wifimodule.databinding.CustomviewListviewProductBinding
import com.example.wifimodule.databinding.FragmentAddMultipleCatalogueBinding
import com.example.wifimodule.domain.getCategory
import com.example.wifimodule.domain.getCatelogue
import com.example.wifimodule.presentation.verify_gst.VerifyGstActivity
import com.example.wifimodule.utils.PermissionCallBack
import com.example.wifimodule.utils.confirmationDialog
import com.example.wifimodule.utils.loadImg
import com.example.wifimodule.utils.picker.FileChooserUtil
import com.example.wifimodule.utils.picker.ImageChooserUtil
import com.example.wifimodule.utils.recyclerview.RecyclerViewLayoutManager
import com.example.wifimodule.utils.recyclerview.RecyclerViewLinearLayout
import com.example.wifimodule.utils.recyclerview.setUpRecyclerView_Binding
import com.example.wifimodule.utils.simpleAlert
import com.example.wifimodule.utils.toIntLocal
import com.example.wifimodule.utils.toRequestBodyFile
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject


@AndroidEntryPoint
class AddMultipleCatalogueFragment :
    BaseFragment<FragmentAddMultipleCatalogueBinding, AddMultipleCatalogueFragVM>() {

    private val TAG = javaClass.simpleName
    private var productsList = ArrayList<Product?>()
    private var categoryList = ArrayList<CategoryData?>()
    private var cataloguesList = ArrayList<CatelogueData?>()

    private var selectedProductPosition = -1
    private var selectedProduct : Product? = null
    private var selectedProductBinding : CustomviewListviewProductBinding? = null

    private var productImageFile: File? = null
    private var photoname: String? = null
    var catelogueId:String? = ""

    var isfromUploadExcel = false

    @Inject
    lateinit var prefs: SharedPrefs

    override val titleToolbar: String?
        get() = "Add Multiple Catalogue"

    override fun observeViewModel() {
        if (arguments?.containsKey("catelogueId") == true){
            catelogueId = arguments?.getString("catelogueId","")
        }
        if (arguments?.containsKey("isFrom") == true) {
            if (arguments?.getString("isFrom").equals("uploadExcel", true)) {
                isfromUploadExcel = true
                productsList = BaseApp.INSTANCE.productsList
                setupProductRecycler()
            }
            if (arguments?.getString("isFrom").equals("Catalogue", true)) {
                isfromUploadExcel = true
                productsList = BaseApp.INSTANCE.productsList
                setupProductRecycler()
            }
        }
        viewModel.apply {
            getCategory(this.viewModelScope, viewModel.repository) {
                handleCategory(it)
            }
            getCatelogue(this.viewModelScope, viewModel.repository) {
                handleCatelogue(it)
            }
        }
    }

    private fun handleCatelogue(it: Resource<CatelogueResponse>?) {
        if (it is Resource.Success) {
            if (it?.data?.status?.code?.equals("200") == true) {
                it.data.data?.let { it1 ->
                    cataloguesList = it1
                }
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
                it.data.data?.let { it1 ->
                    categoryList = it1
                }
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

    private fun setCategorySpinner(binding: CustomviewListviewProductBinding) {
        binding.productDetailsInclude.spnCategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedProduct?.apply {
                    category = categoryList?.get(position)?.name
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Please select at least 1 category!", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.productDetailsInclude.spnCategory.setAdapter(aa)
    }
    private fun setCatalogueSpinner(binding: CustomviewListviewProductBinding) {
        binding.productDetailsInclude.spnCatalogue.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedProduct?.apply {
                    catalogue = cataloguesList?.get(position)?.name
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Please select at least 1 catalogue!", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cataloguesList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.productDetailsInclude.spnCatalogue.setAdapter(aa)
    }

    private fun setupEditText(
        productData: Product,
        editText: EditText,
        binding: CustomviewListviewProductBinding
    ) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                when (editText) {
                    binding.productDetailsInclude.edtProductName -> {
                        productData.name = s.toString().trim()
                    }

                    binding.productDetailsInclude.edtProductDescription -> {
                        productData.description = s.toString().trim()
                    }

                    binding.productDetailsInclude.edtProductFeatures -> {
                        productData.features = s.toString().trim()
                    }

                    binding.productDetailsInclude.edtPrice -> {
                        productData.price = s.toString().trim().toIntLocal()
                    }

                    binding.productDetailsInclude.edtDiscountPer -> {
                        productData.discount = s.toString().trim().toInt().toDouble()
                    }

                    binding.productDetailsInclude.edtReceivePrice -> {
                        productData.finalPrice = s.toString().trim().toIntLocal()
                    }

                    binding.productDetailsInclude.edtProductStock -> {
                        productData.stock = s.toString().trim().toIntLocal()
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun setupProductRecycler() {
        binding.productRecyclerView.setUpRecyclerView_Binding<Product, CustomviewListviewProductBinding>(
            R.layout.customview_listview_product,
            productsList,
            RecyclerViewLayoutManager.LINEAR,
            RecyclerViewLinearLayout.VERTICAL
        ) {
            contentBinder { item, binding, position ->
                val name = "${item.name} ${position.plus(1)}"
                binding.txtTitle.text = name
                binding.inputProductInfoBtn.setOnClickListener {
                    if (!binding.productDetailsLayout.isVisible) {
                        binding.productDetailsLayout.isVisible = true
                        selectProduct(position, item, binding)
                    } else {
                        binding.productDetailsLayout.isGone = true
                        selectProduct(-1, null, binding)
                    }
                    binding.productDetailsInclude.edtProductName.setText(name)
                    setCategorySpinner(binding)
                    setCatalogueSpinner(binding)
                }

                binding.productDetailsInclude.llselectImage.setOnClickListener {
                    selectProduct(position, item, binding)
                    onGallery()
                }

                binding.productDetailsInclude.edtProductName.setOnClickListener {
                    setupEditText(item, it as EditText, binding)
                }
                binding.productDetailsInclude.edtProductStock.setOnClickListener {
                    setupEditText(item, it as EditText, binding)
                }
                binding.productDetailsInclude.edtProductDescription.setOnClickListener {
                    setupEditText(
                        item,
                        binding.productDetailsInclude.edtProductDescription,
                        binding
                    )
                }
                binding.productDetailsInclude.edtProductFeatures.setOnClickListener {
                    setupEditText(item, binding.productDetailsInclude.edtProductFeatures, binding)

                }
                binding.productDetailsInclude.edtPrice.setOnClickListener {
                    setupEditText(item, binding.productDetailsInclude.edtPrice, binding)

                }
                binding.productDetailsInclude.edtDiscountPer.setOnClickListener {
                    setupEditText(item, binding.productDetailsInclude.edtDiscountPer, binding)

                }
                binding.productDetailsInclude.edtReceivePrice.setOnClickListener {
                    setupEditText(item, binding.productDetailsInclude.edtReceivePrice, binding)
                }

//                binding.productDetailsInclude.spnCategory.setOnClickListener {
//                    selectProduct(position, item)
//                }
//
//                binding.productDetailsInclude.spnCategory.setOnClickListener {
//                    selectProduct(position, item)
//                }

                binding.productDetailsInclude.btnSubmit.setOnClickListener {
                    insertProducts(CreateProductRequest(products = arrayListOf(item)))
                }
            }
            isNestedScrollingEnabled = false
        }
    }

    private fun selectProduct(position: Int, product: Product?, binding: CustomviewListviewProductBinding) {
        this.selectedProductPosition = position
        this.selectedProduct = product
        this.selectedProductBinding = binding
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


    override fun initViewBinding() {
        if (!isfromUploadExcel) {
            productsList.add(Product(name = "Product"))
        }
        setupProductRecycler()
        binding.apply {
            addProductItemBtn.setOnClickListener {
                productsList.add(Product(name = "Product"))
                setupProductRecycler()
            }
            saveAllProductsBtn.setOnClickListener {
                var insert = true
                productsList.forEach {
                    insert = it?.name?.isEmpty() == true || it?.price == 0
                }
                if (insert) {
                    insertProducts(CreateProductRequest(productsList))
                } else {
                    Toast.makeText(
                        context,
                        "Product is name or price is empty!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun onGallery() {
        photoname = "IMG_" + System.currentTimeMillis().toString()
        val permissions: ArrayList<String> = ArrayList()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
            permissions.add(Manifest.permission.READ_MEDIA_VIDEO)
            permissions.add(Manifest.permission.READ_MEDIA_AUDIO)
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        requestPermission(permissions, object : PermissionCallBack {
            override fun permissionGranted() {
                ImageChooserUtil.startGalleryIntent(this@AddMultipleCatalogueFragment)
            }

            override fun permissionDenied() {
                requireContext().confirmationDialog(title = getString(R.string.permission),
                    msg = getString(R.string.write_external_storage_title),
                    btnPositiveClick = {
                        onGallery()
                    })
            }

            override fun onPermissionDisabled() {
                requireContext().confirmationDialog(title = getString(R.string.permission),
                    msg = getString(R.string.write_external_storage_title),
                    btnPositiveClick = {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", requireContext().packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    })
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            FileChooserUtil.getFileName(requireContext(), data?.data)?.let { photoname = it }
            when (requestCode) {
                FileChooserUtil.FILE_CHOOSER -> {
                }

                ImageChooserUtil.REQUEST_CAMERA -> {
                    loadingDialog(true)
                    saveImage(requestCode, resultCode, data)
                }

                ImageChooserUtil.REQUEST_GALLERY -> {
                    loadingDialog(true)
                    saveImage(requestCode, resultCode, data)
                }
            }
        }
    }

    fun saveImage(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            ImageChooserUtil.SaveImageTask(data,
                requestCode,
                photoname ?: ("IMG_" + System.currentTimeMillis() + ".jpg"),
                object : ImageChooserUtil.SaveImageTask.FileSaveListener {
                    override fun fileSaved(savedFile: File) {
                        productImageFile = savedFile
                        loadingDialog(false)
                        uploadProductImage()
                    }
                }).execute()
        } else {
            loadingDialog(false)
        }
    }

    private fun uploadProductImage() {
        viewModel.uploadProductImage(productImageFile?.toRequestBodyFile("product-image")) {
            handleUploadProductImage(it)
        }
    }

    private fun handleUploadProductImage(it: Resource<ImageUploadResponse>?) {
        if (it is Resource.Success) {
            loadingDialog(false)
            if (it?.data?.status?.code == 200) {
                selectedProductBinding?.productDetailsInclude?.productImageEdittext?.setText(it?.data?.image)
                Toast.makeText(context, "succ: ${it.data.image}", Toast.LENGTH_SHORT).show()
            }
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