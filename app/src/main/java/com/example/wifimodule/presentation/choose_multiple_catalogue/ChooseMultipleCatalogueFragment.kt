package com.example.wifimodule.presentation.choose_multiple_catalogue

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.wifimodule.R
import com.example.wifimodule.base.BaseApp
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.data.response.ExcelProductResponse
import com.example.wifimodule.databinding.FragmentChooseMultipleCatalogueBinding
import com.example.wifimodule.utils.PermissionCallBack
import com.example.wifimodule.utils.confirmationDialog
import com.example.wifimodule.utils.picker.FileChooserUtil
import com.example.wifimodule.utils.picker.ImageChooserUtil
import com.example.wifimodule.utils.selectFileFrom
import com.example.wifimodule.utils.simpleAlert
import com.example.wifimodule.utils.toRequestBodyFile
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class ChooseMultipleCatalogueFragment :
    BaseFragment<FragmentChooseMultipleCatalogueBinding, ChooseMultipleCatalogueFragVM>() {
    var photoname: String = "IMG_" + System.currentTimeMillis().toString()
    val uiScope = CoroutineScope(Dispatchers.Main)
    val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    val bgDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            inputProductInfoBtn.setOnClickListener {
                findNavController().navigate(R.id.addMultipleCatalogueFragment, Bundle.EMPTY)
            }
            uploadProductInfoBtn.setOnClickListener {
                callImagePickerDialog()
            }
        }
    }

    private fun callImagePickerDialog() {
        requireContext().selectFileFrom(onFileClick = { onPDF() }, onCamera = {
            photoname = "IMG_" + System.currentTimeMillis().toString(); onCapture(
            photoname
        )
        }, onGallery = { onGallery() })
    }

    fun onPDF() {
        val permissions: ArrayList<String> = ArrayList()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            FileChooserUtil.openChooserDialog(
                this@ChooseMultipleCatalogueFragment, FileChooserUtil.FILE_CHOOSER
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            requestPermission(permissions, object : PermissionCallBack {
                override fun permissionGranted() {
                    FileChooserUtil.openChooserDialog(
                        this@ChooseMultipleCatalogueFragment, FileChooserUtil.FILE_CHOOSER
                    )
                }

                override fun permissionDenied() {
                    activity?.confirmationDialog(title = getString(R.string.permission),
                        msg = getString(R.string.write_external_storage_title),
                        btnPositiveClick = {
                            onPDF()
                        })
                }

                override fun onPermissionDisabled() {
                    activity?.confirmationDialog(title = getString(R.string.permission),
                        msg = getString(R.string.write_external_storage_title),
                        btnPositiveClick = {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts("package", activity?.packageName, null)
                            intent.data = uri
                            activity?.startActivity(intent)
                        })
                }
            })
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
                ImageChooserUtil.startGalleryIntent(this@ChooseMultipleCatalogueFragment)
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

    fun onCapture(photo: String?) {
        photoname = photo ?: "IMG_" + System.currentTimeMillis().toString()
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
                ImageChooserUtil.startCameraIntent(
                    this@ChooseMultipleCatalogueFragment,
                    photoname ?: "IMG_" + System.currentTimeMillis().toString()
                )
            }

            override fun permissionDenied() {
                requireContext().confirmationDialog(title = getString(R.string.permission),
                    msg = getString(R.string.write_external_storage_title),
                    btnPositiveClick = {
                        onCapture(photoname)
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
//                    callUploadGstAPI(File(RealPathUtil.getRealPath(requireContext(), data?.data)))
                    savePDf(data, resultCode)
                }

                ImageChooserUtil.REQUEST_CAMERA -> {
                    loadingDialog(true)
//                    saveImage(requestCode, resultCode, data)
                }

                ImageChooserUtil.REQUEST_GALLERY -> {
                    loadingDialog(true)
//                    saveImage(requestCode, resultCode, data)
                }
            }
        }
    }

    //    fun saveImage(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == AppCompatActivity.RESULT_OK) {
//            ImageChooserUtil.SaveImageTask(data,
//                requestCode,
//                photoname ?: ("IMG_" + System.currentTimeMillis() + ".jpg"),
//                object : ImageChooserUtil.SaveImageTask.FileSaveListener {
//                    override fun fileSaved(savedFile: File) {
//                        binding.selectGst.loadImg(
//                            savedFile.absolutePath,
//                            R.drawable.upload_with_background
//                        )
//                        gstFile = savedFile
//                        loadingDialog(false)
//                    }
//                }).execute()
//        } else {
//            loadingDialog(false)
//        }
//    }
    fun savePDf(data: Intent?, resultCode: Int) {
        if (resultCode == AppCompatActivity.RESULT_OK) {

            val selectedUri = data?.data

            val destinationFile = File(
                context?.filesDir?.absolutePath, "IMG_" + System.currentTimeMillis() + ".xlsx"
            )

            if (selectedUri != null) {

                val cursor = context?.contentResolver?.query(selectedUri, null, null, null, null)
                cursor?.moveToFirst()
                val size = cursor?.getLong(cursor.getColumnIndex(OpenableColumns.SIZE))
                cursor?.close()

                size?.let {

                    if ((size / 1024) <= 5120) {

//                    val sourceFile = File(getPath(data?.data))

                        uiScope.launch {
                            withContext(bgDispatcher) {

                                try {
                                    var bytesum = 0
                                    var byteread = 0
                                    val inStream =
                                        context?.contentResolver?.openInputStream(selectedUri)
                                    val fs = FileOutputStream(destinationFile)
                                    val buffer = ByteArray(1444)
                                    while (inStream?.read(buffer).also {
                                            if (it != null) {
                                                byteread = it
                                            }
                                        } != -1) {
                                        bytesum += byteread
                                        fs.write(buffer, 0, byteread)
                                    }
                                    inStream?.close()
                                    fs.close()
                                } catch (e: IOException) {
                                    // handle exception here
                                    e.printStackTrace()
                                } catch (e: Exception) {
                                    // handle exception here
                                    e.printStackTrace()
                                }
                            }
                            withContext(uiDispatcher) {
                                if (destinationFile.isFile) {
                                    callUploadGstAPI(destinationFile)
                                }

                            }
                        }
                    } else {
//                        toast(getString(R.string.file_validation))
                    }
                }
            } else {
//                toast(getString(R.string.file_not_found))
            }
        }
    }

    private fun callUploadGstAPI(savedFile: File?) {
        viewModel.uploadCatelogCSV(savedFile?.toRequestBodyFile("excel-file"), {
            handleUploadGst(it)
        })
    }

    private fun handleUploadGst(it: Resource<ExcelProductResponse>?) {
        if (it is Resource.Success) {
            it.data?.data?.let { BaseApp.INSTANCE.productsList = it }
            findNavController().navigate(R.id.addMultipleCatalogueFragment,
                Bundle().apply { putString("isFrom", "uploadExcel") })
            loadingDialog(false)
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
}