package com.dondja.dondja.util

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["isVisible"])
fun View.isVisible(value: Boolean?) {
    isVisible = value == true
}