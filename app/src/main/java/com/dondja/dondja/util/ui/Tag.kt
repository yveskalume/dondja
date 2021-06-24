package com.dondja.dondja.util.ui

import androidx.core.view.children
import androidx.databinding.BindingAdapter
import com.afollestad.materialdialogs.utils.MDUtil.inflate
import com.dondja.dondja.data.entity.Theme
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("setThemes","themeClickListener")
fun ChipGroup.setThemes(themes: List<String>,themeClickListener: ThemeClickListener) {
    for (item in themes) {
        val chip = Chip(this.context).apply {
            text = item
            isCheckable = true
            isClickable = true

            setOnCheckedChangeListener { buttonView, _ ->
                themeClickListener.onThemeClick(buttonView.text.toString())
            }
        }
        addView(chip)
    }
}

interface ThemeClickListener {
    fun onThemeClick(themeName: String)
}