package com.dondja.dondja.ui.fragment.themefeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentThemeFeedBinding
import com.dondja.dondja.post

class ThemeFeedFragment : Fragment(R.layout.fragment_theme_feed) {
    private val binding by viewBinding<FragmentThemeFeedBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            for (i in 1..6) {
                post {
                    id("post")
                    onPostClick { _ ->

                    }
                }
            }
        }
    }
}