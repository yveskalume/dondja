package com.dondja.dondja.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dondja.dondja.databinding.WelcomeTextBinding

class WelcomeTextPagerAdapter : ListAdapter<String, WelcomeTextPagerAdapter.CustomViewHolder>(Companion) {

    private val welcomeText = listOf(
        "Les réseaux sociaux sont semblable à l’épée elle peut sauver une vie ou la detruire",
        "Tout depend de la main qui la manie \n\uD83E\uDD27",
        "Nous esperons que vous utiliserez Dondja pour “ Sauver des vies “ remplissant des joie et de rire la vie des autres par vos postes \n \uD83E\uDD70",
        "“ Le monde se meurt par manque d’un peu d’amour “ \n \uD83D\uDE05",
        "Bienvenu(e) sur Dondja \n\uD83D\uDE18"
    )

    init {
        this.submitList(welcomeText)
    }

    companion object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == oldItem.hashCode()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WelcomeTextBinding.inflate(layoutInflater,parent,false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = welcomeText[position]
        holder.binding.textView.text = currentItem
    }

    class CustomViewHolder(val binding: WelcomeTextBinding) : RecyclerView.ViewHolder(binding.root)
}