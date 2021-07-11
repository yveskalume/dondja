package com.dondja.dondja.util.ui

import android.annotation.SuppressLint
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
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

@BindingAdapter(value = ["isVisible"])
fun View.isVisible(value: Boolean?) {
    isVisible = value == true
}

@BindingAdapter(value = ["longText"])
fun TextView.longText(value: Long) {
    text = value.toString()
}

@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("bindLikeBtn")
fun ImageView.bindLikeButton(isLiked: Boolean) {
    val image = when(isLiked) {
        false -> resources.getDrawable(R.drawable.ic_like)
        true ->  resources.getDrawable(R.drawable.ic_red_heart)
    }

    setImageDrawable(image)
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

@BindingAdapter(value = ["setImagesWithourDot"])
fun ImageCarousel.setImagesWithourDot(items: List<String>) {
    val list = items.map { CarouselItem(it) }
    showIndicator = false
    setData(list)
}

@BindingAdapter(value = ["bindDate"])
fun TextView.bindDate(date: Date) {
    val p = PrettyTime()
    text = p.format(date)
}

@BindingAdapter(value = ["onCarouselClick"])
fun ImageCarousel.onCarouselClick(listener : (Unit) -> Unit) {
    carouselListener = object  : CarouselListener {
        override fun onClick(position: Int, carouselItem: CarouselItem) {
            super.onClick(position, carouselItem)
            listener.invoke(Unit)
        }
    }
}
