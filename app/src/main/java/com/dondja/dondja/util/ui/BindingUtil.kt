package com.dondja.dondja.util.ui

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dondja.dondja.R
import com.dondja.dondja.ui.adapter.sliderimage.SliderImageAdapter
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@BindingAdapter(value = ["isVisible"])
fun View.isVisible(value: Boolean?) {
    isVisible = value == true
}

@BindingAdapter("setWithPager")
fun EpoxyRecyclerView.setUpWithPager(rv: EpoxyRecyclerView) {
    withModels {

    }
}

@BindingAdapter(value = ["setImageRes"], requireAll = true)
fun ImageView.setImageRes(resId: Int) {
    setImageResource(resId)
}

@BindingAdapter(value = ["setImageUrl"], requireAll = false)
fun ImageView.setImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().placeholder(android.R.color.darker_gray))
            .into(this)
    }
}

@BindingAdapter(value = ["setBitmap"], requireAll = false)
fun ImageView.setBitmapImage(bitmap: Bitmap) {
    setImageBitmap(bitmap)
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

@BindingAdapter(value = ["textLong"])
fun TextView.setTextFromLong(content: Long) {
    text = content.toString()
}

@BindingAdapter(value = ["setImages"])
fun SliderView.setImages(items: List<String>) {
    setSliderAdapter(SliderImageAdapter(items))
    setSliderTransformAnimation(SliderAnimations.ANTICLOCKSPINTRANSFORMATION)
}

@BindingAdapter(value = ["setImages"])
fun ImageCarousel.setImages(items: List<String>) {
    val list = items.map { CarouselItem(it) }
    if (items.size < 2) {
        showIndicator = false
    }
    setData(list)
}
