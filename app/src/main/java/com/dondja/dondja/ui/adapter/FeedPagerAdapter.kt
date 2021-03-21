package com.dondja.dondja.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.dondja.dondja.databinding.LayoutFeedRecyclerviewBinding
import com.dondja.dondja.util.CustomViewHolder

class FeedPagerAdapter(private val context: Context) : RecyclerView.Adapter<CustomViewHolder>() {
    val rv : EpoxyRecyclerView = EpoxyRecyclerView(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutFeedRecyclerviewBinding.inflate(layoutInflater,parent,false)
        return CustomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding
    }

    override fun getItemCount(): Int {
        return 1
    }


}