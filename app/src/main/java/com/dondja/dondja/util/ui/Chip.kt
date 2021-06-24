package com.dondja.dondja.util.ui

import androidx.core.view.children
import androidx.databinding.BindingAdapter
import com.afollestad.materialdialogs.utils.MDUtil.inflate
import com.dondja.dondja.data.entity.Theme
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("setThemes","themeClickListener")
fun ChipGroup.setThemes(themes: List<Theme>,themeClickListener: ThemeClickListener) {
    for (item in themes) {
        val chip = Chip(this.context).apply {
            text = item.name
            isCheckable = true
            isClickable = true

            setOnCheckedChangeListener { buttonView, isChecked ->
                themeClickListener.onThemeClick(themes[buttonView.id])
            }
        }
        addView(chip)
    }

}

interface ThemeClickListener {
    fun onThemeClick(theme: Theme)
}