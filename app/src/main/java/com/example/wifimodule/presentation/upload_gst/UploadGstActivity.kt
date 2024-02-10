package com.example.wifimodule.presentation.upload_gst

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.example.wifimodule.R
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.data.response.GstUploadResponse
import com.example.wifimodule.databinding.ActivityUploadGstBinding
import com.example.wifimodule.presentation.verify_gst.VerifyGstActivity
import com.example.wifimodule.utils.PermissionCallBack
import com.example.wifimodule.utils.confirmationDialog
import com.example.wifimodule.utils.loadImg
import com.example.wifimodule.utils.picker.FileChooserUtil
import com.example.wifimodule.utils.picker.ImageChooserUtil
import com.example.wifimodule.utils.simpleAlert
import com.example.wifimodule.utils.takePick
import com.example.wifimodule.utils.toRequestBodyFile
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class UploadGstActivity : BaseActivity<ActivityUploadGstBinding, UploadGstActivityVM>() {
    var photoname: String = "IMG_" + System.currentTimeMillis().toString()
    var gstFile: File? = null

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            btnUpload.setOnClickListener {
                if (gstFile == null) {
                    simpleAlert("Please select Gst Certificate")
                } else {
                    callUploadGstAPI(gstFile)
                }
            }
            selectGst.setOnClickListener { callImagePickerDialog() }
            txtSkip.setOnClickListener {
                startActivity(
                    Intent(
                        this@UploadGstActivity,
                        VerifyGstActivity::class.java
                    )
                )
            }
        }
    }

    private fun handleUploadGst(it: Resource<GstUploadResponse>?) {
        if (it is Resource.Success) {
            startActivity(Intent(this@UploadGstActivity, VerifyGstActivity::class.java))
            loadingDialog(false)
        }
        if (it is Resource.Loading) {
            loadingDialog(true)
        }
        if (it is Resource.Error) {
            loadingDialog(false)
            it.message?.let { it1 -> simpleAlert(it1) }
        }
        if (it is Resource.APIException) {
            loadingDialog(false)
            it.message?.let { it1 -> simpleAlert(it1) }
        }
        if (it is Resource.Idle) {

        }
    }

    private fun callImagePickerDialog() {
        takePick(
            onCamera = {
                photoname = "IMG_" + System.currentTimeMillis().toString(); onCapture(
                photoname
            )
            },
            onGallery = { onGallery() }
        )
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
        requestPermission(permissions, object :
            PermissionCallBack {
            override fun permissionGranted() {
                ImageChooserUtil.startGalleryIntent(this@UploadGstActivity)
            }

            override fun permissionDenied() {
                confirmationDialog(
                    title = getString(R.string.permission),
                    msg = getString(R.string.write_external_storage_title),
                    btnPositiveClick = {
                        onGallery()
                    }
                )
            }

            override fun onPermissionDisabled() {
                confirmationDialog(
                    title = getString(R.string.permission),
                    msg = getString(R.string.write_external_storage_title),
                    btnPositiveClick = {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                )
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
                    this@UploadGstActivity,
                    photoname ?: "IMG_" + System.currentTimeMillis().toString()
                )
            }

            override fun permissionDenied() {
                confirmationDialog(
                    title = getString(R.string.permission),
                    msg = getString(R.string.write_external_storage_title),
                    btnPositiveClick = {
                        onCapture(photoname)
                    }
                )
            }

            override fun onPermissionDisabled() {
                confirmationDialog(
                    title = getString(R.string.permission),
                    msg = getString(R.string.write_external_storage_title),
                    btnPositiveClick = {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                )
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            FileChooserUtil.getFileName(this, data?.data)?.let { photoname = it }
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
                        binding.selectGst.loadImg(
                            savedFile.absolutePath,
                            R.drawable.upload_with_background
                        )
                        gstFile = savedFile
                        loadingDialog(false)
                    }
                }).execute()
        } else {
            loadingDialog(false)
        }
    }

    private fun callUploadGstAPI(savedFile: File?) {
        viewModel.uploadGstCertificate(savedFile?.toRequestBodyFile("gst-certificate"), {
            handleUploadGst(it)
        })
    }

}