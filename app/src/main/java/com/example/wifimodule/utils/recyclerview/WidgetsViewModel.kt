package com.app.lifetidy.utils.ktx

import androidx.annotation.LayoutRes

interface WidgetsViewModel {
    @LayoutRes
    fun layoutId(): Int
}