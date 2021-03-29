package com.dondja.dondja.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import com.dondja.dondja.R
import com.makeramen.roundedimageview.RoundedImageView

@BindingAdapter("setWithPager")
fun EpoxyRecyclerView.setUpWithPager(rv: EpoxyRecyclerView) {
    withModels {

    }
}

@BindingAdapter(value = ["setRandomImage"])
fun ImageView.setRandomImage(boolean: String) {
    val images = listOf(R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6)
    setImageResource(images.random())
}
