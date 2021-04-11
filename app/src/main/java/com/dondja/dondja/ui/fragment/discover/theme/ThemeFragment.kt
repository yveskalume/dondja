package com.dondja.dondja.ui.fragment.discover.theme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentThemeBinding
import com.dondja.dondja.discover
import com.dondja.dondja.theme

class ThemeFragment : Fragment(R.layout.fragment_theme) {

    private val binding by viewBinding<FragmentThemeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            for (i in 1..8) {
                theme {
                    id(i)
                }
            }
        }
    }
}