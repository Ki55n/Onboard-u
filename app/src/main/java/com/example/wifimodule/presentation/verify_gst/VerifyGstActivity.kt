package com.example.wifimodule.presentation.verify_gst

import android.content.Intent
import com.example.wifimodule.R
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseActivity
import com.example.wifimodule.data.request.GstRequest
import com.example.wifimodule.data.response.VerifyGstResponse
import com.example.wifimodule.databinding.ActivityVerifyGstBinding
import com.example.wifimodule.presentation.gst_otp.GstOtpActivity
import com.example.wifimodule.utils.simpleAlert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyGstActivity : BaseActivity<ActivityVerifyGstBinding, VerifyGstActivityVM>() {

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            btnConfirm.setOnClickListener {
                callVerifyGstAPI()
            }
        }
    }

    private fun callVerifyGstAPI() {
        if (isValid()) {
            var request = GstRequest(
                businessAddress=binding.edtAddress.text.toString().trim(),
                    gstNo=binding.edtGstNo.text.toString().trim(),
                    gstType=binding.edtGstType.text.toString().trim(),
                    legalName=binding.edtLegalName.text.toString().trim(),
                    tradeName=binding.edtTradeName.text.toString().trim()
            )
            viewModel.verifyGst(request){
                handleResponse(it)
            }
        }
    }

    private fun handleResponse(it: Resource<VerifyGstResponse>?) {
        if (it is Resource.Success) {
            startActivity(Intent(this@VerifyGstActivity, GstOtpActivity::class.java).apply {
                putExtra("seller_id",it.data?.data?.sellerId)
                putExtra("seller_name",it.data?.data?.tradeName)
            })
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

    fun isValid(): Boolean {
        if (binding.edtTradeName.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.trade_name_empty))
            return false
        } else if (binding.edtGstNo.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.gst_no_empty))
            return false
        } else if (binding.edtGstType.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.gst_type_empty))
            return false
        } else if (binding.edtLegalName.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.legal_name_empty))
            return false
        } else if (binding.edtAddress.text.trim().isEmpty()) {
            simpleAlert(getString(R.string.gst_address_empty))
            return false
        }
        return true
    }

}