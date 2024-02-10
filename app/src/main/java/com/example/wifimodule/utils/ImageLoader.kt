package com.example.wifimodule.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.wifimodule.R
import java.io.File

fun ImageView.loadImg(url: String?, @DrawableRes res: Int = R.mipmap.ic_launcher) {
    url?.let {
        Glide.with(this).load(if (it.isWebUrl() == true) it else File(it)).placeholder(res).into(this)
    }
}

fun String?.isWebUrl(): Boolean? {
    if (this == null){
        return false
    }else{
        return android.util.Patterns.WEB_URL.matcher(this).matches();}
}