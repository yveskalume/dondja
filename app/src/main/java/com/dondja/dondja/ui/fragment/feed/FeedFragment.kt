package com.dondja.dondja.ui.fragment.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentFeedBinding
import com.dondja.dondja.ui.adapter.FeedPagerAdapter

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding<FragmentFeedBinding>()

    fun setUpFeed() {
        FeedPagerAdapter(requireContext()).rv.withModels {

        }
    }

}