package com.example.wifimodule.utils

import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap
import com.example.wifimodule.base.BaseApp
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


fun String?.toRequestBody(): RequestBody? {
    return this?.toRequestBody("text/plain".toMediaTypeOrNull())
}

fun String.toRequestBody(key: String): RequestBody {
    val body = MultipartBody.Builder().setType(MultipartBody.FORM)
    return body.addFormDataPart(key, this).build()
}

fun File.toRequestBodyFile(key: String): MultipartBody.Part {
    val filePart = MultipartBody.Part.createFormData(
        key,
        this.name,
        this.asRequestBody((getMimeType(this) ?: "application/octet-stream").toMediaTypeOrNull())
    )

    return filePart
}

fun getMimeType(file: File?): String? {
    val uri: Uri = Uri.fromFile(file)
    val cR: ContentResolver = BaseApp.INSTANCE.contentResolver
    val mime = MimeTypeMap.getSingleton()
    return mime.getExtensionFromMimeType(cR.getType(uri))
}

fun getMimeType(url: String?): String? {
    var type: String? = null
    val extension = MimeTypeMap.getFileExtensionFromUrl(url)
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
    return type
}