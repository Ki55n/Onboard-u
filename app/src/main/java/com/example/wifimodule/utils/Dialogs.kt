package com.example.wifimodule.utils

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.app.lifetidy.utils.prettyDialog.PrettyDialog
import com.example.wifimodule.R
import com.example.wifimodule.databinding.DialogSelectFileBinding
import com.example.wifimodule.databinding.DialogSelectImageCameraBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun Context.simpleAlert(msg: String, positiveButton: (() -> Unit)? = null) {
    simpleAlert(null, msg, positiveButton)
//    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
//    mDialog.setCanceledOnTouchOutside(false)
//    mDialog.setCancelable(false)
//    mDialog.setIcon(R.mipmap.ic_launcher_round)
//        .setMessage(msg)
//        .addButton(getString(R.string.ok), R.color.white, R.color.purple_700) {
//            positiveButton?.invoke()
//            mDialog.dismiss()
//        }.show()
}

fun Context.simpleAlert(
    title: String?,
    msg: String?,
    positiveButton: (() -> Unit)? = null,
) {
    simpleAlert(title, msg, getString(R.string.ok), positiveButton)
//    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
//    mDialog.setCanceledOnTouchOutside(false)
//    mDialog.setCancelable(false)
//    mDialog.setIcon(icon)
//    mDialog.setTitle(title)
//    msg?.let { mDialog.setMessage(it) }
//    mDialog.addButton(getString(R.string.ok), R.color.white, R.color.purple_700) {
//        positiveButton?.invoke()
//        mDialog.dismiss()
//    }.show()
}

fun Context.simpleAppAlert(
    msg: String?,
    positiveButton: (() -> Unit)? = null,
    icon: Int? = R.mipmap.ic_launcher
) {
    simpleAlert(getString(R.string.app_name), msg, getString(R.string.ok), positiveButton, icon)
//    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
//    mDialog.setCanceledOnTouchOutside(false)
//    mDialog.setCancelable(false)
//    mDialog.setIcon(icon)
//    mDialog.setTitle(title)
//    msg?.let { mDialog.setMessage(it) }
//    mDialog.addButton(getString(R.string.ok), R.color.white, R.color.purple_700) {
//        positiveButton?.invoke()
//        mDialog.dismiss()
//    }.show()
}

fun Context.simpleAlert(
    title: String?,
    msg: String?,
    btnTitle: String,
    positiveButton: (() -> Unit)? = null,
    icon: Int? = R.drawable.ic_information
) {
    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
    mDialog.setCanceledOnTouchOutside(false)
    mDialog.setCancelable(false)
    mDialog.setIcon(icon)
    mDialog.setTitle(title)
    msg?.let { mDialog.setMessage(it) }
    mDialog.addButton(btnTitle, R.color.white, R.color.app_blue) {
        positiveButton?.invoke()
        mDialog.dismiss()
    }.show()
}

fun Context.appForceUpdate(
    title: String,
    msg: String,
    btnTitle: String,
    positiveButton: (() -> Unit)? = null
) {
    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
    mDialog.setCanceledOnTouchOutside(false)
    mDialog.setCancelable(false)
    mDialog.setIcon(R.drawable.ic_information)
        .setTitle(title)
        .setMessage(msg)
        .addButton(btnTitle, R.color.white, R.color.purple_700) {
            positiveButton?.invoke()
        }.show()
}

fun Context.confirmationDialog(
    msg: String,
    btnPositiveClick: (() -> Unit)? = null,
    btnNegativeClick: (() -> Unit)? = null
) {
    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
    mDialog.setCanceledOnTouchOutside(false)
    mDialog.setCancelable(false)
    mDialog.setIcon(R.drawable.ic_information)
        //.setTitle(getString(R.string.application_name))
        .setMessage(msg)
        .addButton(getString(R.string.yes), R.color.white, R.color.color_a30000) {
            btnPositiveClick?.invoke()
            mDialog.dismiss()
        }
        .addButton(getString(R.string.cancel), R.color.white, R.color.app_blue) {
            btnNegativeClick?.invoke()
            mDialog.dismiss()
        }
        .show()
}

fun Context.confirmationDialog(
    title: String,
    msg: String,
    btnPositiveClick: (() -> Unit)? = null,
    btnNegativeClick: (() -> Unit)? = null
) {
    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
    mDialog.setCanceledOnTouchOutside(false)
    mDialog.setCancelable(false)
    mDialog.setIcon(R.drawable.ic_information)
        .setTitle(title)
        .setMessage(msg)
        .addButton(getString(R.string.yes), R.color.white, R.color.color_a30000) {
            btnPositiveClick?.invoke()
            mDialog.dismiss()
        }.addButton(getString(R.string.no), R.color.white, R.color.app_blue) {
            btnNegativeClick?.invoke()
            mDialog.dismiss()
        }.show()
}

fun Context.confirmationDialog(
    title: String,
    msg: String,
    btnPositive: String,
    btnNegative: String,
    btnPositiveClick: (() -> Unit)? = null,
    btnNegativeClick: (() -> Unit)? = null,
    icon: Int? = R.drawable.ic_information
) {
    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
    mDialog.setCanceledOnTouchOutside(false)
    mDialog.setCancelable(false)
    mDialog.setIcon(icon)
        .setTitle(title)
        .setMessage(msg)
        .addButton(btnNegative, R.color.white, R.color.color_a30000) {
            btnNegativeClick?.invoke()
            mDialog.dismiss()
        }.addButton(btnPositive , R.color.white, R.color.app_blue) {
            btnPositiveClick?.invoke()
            mDialog.dismiss()
        }.show()
}

fun Context.confirmationDialog(
    title: String,
    msg: String,
    btnPositive: String,
    btnPositiveClick: (() -> Unit)? = null,
    icon: Int? = R.drawable.ic_information
) {
    val mDialog = PrettyDialog(this)
//    mDialog.setTypeface(this.font(R.font.montserrat_regular))
    mDialog.setCanceledOnTouchOutside(false)
    mDialog.setCancelable(false)
    mDialog.setIcon(icon)
        .setTitle(title)
        .setMessage(msg)
        .addButton(btnPositive, R.color.white, R.color.app_blue) {
            btnPositiveClick?.invoke()
            mDialog.dismiss()
        }.show()
}

fun Context.takePick(onGallery: (() -> Unit)? = null, onCamera: (() -> Unit)? = null): BottomSheetDialogBuilder {
    val dialogBinding = DialogSelectImageCameraBinding.inflate(LayoutInflater.from(this))
    val selectFileDialog = bottomSheetDialog({
        customView(dialogBinding.root)
        cancelable(false)
    }, theme = R.style.TransparentDialog)
    selectFileDialog?.show()
    dialogBinding.btnCancel?.setOnClickListener { selectFileDialog?.dismiss() }
    dialogBinding.txtGallery?.setOnClickListener { selectFileDialog?.dismiss();onGallery?.invoke() }
    dialogBinding.txtCamera?.setOnClickListener { selectFileDialog?.dismiss(); onCamera?.invoke() }
    return selectFileDialog



//    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
//    mDialog.setTitle("Select Picture")
//        .setItems(R.array.select_image_from) { dialogInterface, which ->
//            when (which) {
//                0 -> {
//                    dialogInterface.dismiss()
//                    /**Open gallery*/
//                    onGallery?.invoke()
//                }
//                1 -> {
//                    dialogInterface.dismiss()
//                    /**Open camera*/
//                    onCamera?.invoke()
//                }
//            }
//        }
//        .create().show()
}

//fun Context.accountTypes(onItemSelected: ((item: String) -> Unit)? = null) {
//    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
//    val data = resources.getStringArray(R.array.accountTypes)
//    mDialog.setTitle("Account Type")
//        .setItems(data) { dialogInterface, which ->
//            dialogInterface.dismiss()
//            onItemSelected?.invoke(data[which])
//        }
//        .create().show()
//}

fun Context.showListDialog(
    title: String?,
    list: ArrayList<String>,
    onItemSelected: ((item: String) -> Unit)? = null
) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    val data = list.toTypedArray()
    mDialog.setTitle(title)
        .setItems(data) { dialogInterface, which ->
            dialogInterface.dismiss()
            onItemSelected?.invoke(data[which])
        }
        .create().show()
}

fun Context.showListDialog(
    title: String?,
    data: Array<String>,
    onItemSelected: ((item: String) -> Unit)? = null
) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(title)
        .setItems(data) { dialogInterface, which ->
            dialogInterface.dismiss()
            onItemSelected?.invoke(data[which])
        }
        .create().show()
}

fun Context.showListDialog(
    @StringRes title: Int,
    @ArrayRes list: Int,
    onItemSelected: ((item: String) -> Unit)? = null
) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    val data = resources.getTextArray(list)
    mDialog.setTitle(title)
        .setItems(list) { dialogInterface, which ->
            dialogInterface.dismiss()
            onItemSelected?.invoke(data[which] as String)
        }
        .create().show()
}

fun Context.showListDialog(
    @StringRes title: Int,
    data: Array<String>,
    onItemSelected: ((item: String, which: Int) -> Unit)? = null
) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(title)
        .setItems(data) { dialogInterface, which ->
            dialogInterface.dismiss()
            onItemSelected?.invoke(data[which], which)
        }
        .create().show()
}


fun <T> Context.showCustomListDialog(
    title: String?,
    list: ArrayList<T>,
    onItemSelected: ((item: T) -> Unit)? = null
) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    val data: Array<String?> = arrayOfNulls(list.size)
    list.forEachIndexed { index, t -> data[index] = t.toString() }
    mDialog.setTitle(title)
        .setItems(data) { dialogInterface, which ->
            dialogInterface.dismiss()
            onItemSelected?.invoke(list[which])
        }
        .create().show()
}

fun <T> Context.showCustomListDialog(
    @StringRes title: Int,
    list: ArrayList<T>,
    onItemSelected: ((item: T) -> Unit)? = null
) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    val data: Array<String?> = arrayOfNulls(list.size)
    list.forEachIndexed { index, t -> data[index] = t.toString() }
    mDialog.setTitle(title)
        .setItems(data) { dialogInterface, which ->
            dialogInterface.dismiss()
            onItemSelected?.invoke(list[which])
        }
        .create().show()
}

fun Context.showCustomAlert(alertDialog: AlertDialog.Builder.() -> Unit) =
    AlertDialog.Builder(this).apply(alertDialog)


fun Context.selectFileFrom(onFileClick: (() -> Unit)? = null, onScanDocument: (() -> Unit)? = null,onGallery: (() -> Unit)? = null,onCamera: (() -> Unit)? = null): BottomSheetDialogBuilder {
    val dialogBinding = DialogSelectFileBinding.inflate(
        LayoutInflater.from(this)
    )
    val selectFileDialog = bottomSheetDialog({
        customView(dialogBinding.root)
        cancelable(false)
    }, theme = R.style.TransparentDialog)
    selectFileDialog?.show()
    dialogBinding.btnCancel?.setOnClickListener { selectFileDialog?.dismiss() }
    dialogBinding.txtScanDocument?.setOnClickListener { onScanDocument?.invoke();selectFileDialog?.dismiss() }
    dialogBinding.txtFile?.setOnClickListener {  onFileClick?.invoke();selectFileDialog?.dismiss() }
    dialogBinding.txtGallery?.setOnClickListener {  onGallery?.invoke();selectFileDialog?.dismiss() }
    dialogBinding.txtCamera?.setOnClickListener {  onCamera?.invoke();selectFileDialog?.dismiss() }
    return selectFileDialog
}

fun loadWebView(
    webViewTerms: WebView?,
    url: String?,
    onPageLoad: () -> Unit
) {
    val settings = webViewTerms?.settings
    settings?.javaScriptEnabled = true
    settings?.loadWithOverviewMode = true
    settings?.useWideViewPort = true
    settings?.builtInZoomControls = false
    settings?.displayZoomControls = false
    webViewTerms?.webChromeClient = WebChromeClient()
    webViewTerms?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

    webViewTerms?.webViewClient = object : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            onPageLoad.invoke()
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
//                webViewTerms?.loadUrl(request?.url.toString())
            return false

        }
    }
    url?.let { webViewTerms?.loadUrl(it) }
}

fun Context.setDatePickerDialog(dateFormate: String = API_DATE_FORMAT,onDateSelected: (String) -> Unit) {
    val newCalendar: Calendar = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat(dateFormate ?: "dd/mm/yyyy")
    val fromDatePickerDialog = DatePickerDialog(
        this,
        { view, year, monthOfYear, dayOfMonth ->
            val newDate: Calendar = Calendar.getInstance()
            newDate.set(year, monthOfYear, dayOfMonth)
            onDateSelected.invoke(dateFormatter.format(newDate.time))
        },
        newCalendar.get(Calendar.YEAR),
        newCalendar.get(Calendar.MONTH),
        newCalendar.get(Calendar.DAY_OF_MONTH)
    )
    fromDatePickerDialog.show()
}

 fun Context.createDialogWithoutDateField(dateFormate: String= API_DATE_FORMAT,onDateSelected: (String) -> Unit) {
    val newCalendar: Calendar = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat(dateFormate ?: "MM/yyyy")
    val datePickerDialog = DatePickerDialog(
        this,
        android.app.AlertDialog.THEME_HOLO_LIGHT,
        { view, year, monthOfYear, dayOfMonth ->
            val newDate: Calendar = Calendar.getInstance()
            newDate.set(year, monthOfYear, dayOfMonth)
            onDateSelected.invoke(dateFormatter.format(newDate.time))
        },
        newCalendar.get(Calendar.YEAR),
        newCalendar.get(Calendar.MONTH),
        newCalendar.get(Calendar.DAY_OF_MONTH)
    )
        (datePickerDialog.datePicker as ViewGroup).findViewById<View>(
            Resources.getSystem().getIdentifier("day", "id", "android")
        )?.visibility =
            View.GONE
    datePickerDialog.show()
}
