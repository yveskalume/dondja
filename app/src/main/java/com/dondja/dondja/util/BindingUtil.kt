package com.dondja.dondja.util

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import com.dondja.dondja.R

@BindingAdapter(value = ["isVisible"])
fun View.isVisible(value: Boolean?) {
    isVisible = value == true
}

@BindingAdapter("setWithPager")
fun EpoxyRecyclerView.setUpWithPager(rv: EpoxyRecyclerView) {
    withModels {

    }
}

@BindingAdapter(value = ["setRandomImage"])
fun ImageView.setRandomImage(boolean: String) {
    val images = listOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6)
    setImageResource(images.random())
}