package com.dondja.dondja.ui.adapter.sliderimage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.dondja.dondja.databinding.PostImageSliderLayoutBinding
import com.dondja.dondja.util.ui.setImageUrl
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderImageAdapter(private val images: List<String>) : SliderViewAdapter<SliderImageAdapter.SliderImageViewHolder>() {

    override fun getCount() = images.size

    override fun onCreateViewHolder(parent: ViewGroup): SliderImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PostImageSliderLayoutBinding.inflate(layoutInflater,parent,false)
        return SliderImageViewHolder(binding)
    }

    override fun onBindViewHolder(
        viewHolder: SliderImageViewHolder,
        position: Int
    ) {
        val binding = viewHolder.binding as PostImageSliderLayoutBinding
        binding.imageView.setImageUrl(images[position])
        binding.executePendingBindings()
    }

    class SliderImageViewHolder(val binding: ViewBinding) : SliderViewAdapter.ViewHolder(binding.root)

}