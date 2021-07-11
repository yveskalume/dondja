package com.dondja.dondja.ui.fragment.discover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentDiscoverBinding
import com.dondja.dondja.ui.adapter.FragmentPagerAdapter
import com.dondja.dondja.ui.fragment.discover.direct.DirectFragment
import com.dondja.dondja.ui.fragment.discover.foryou.ForYouFragment
import com.dondja.dondja.ui.fragment.discover.popular.PopularFragment
import com.dondja.dondja.ui.fragment.discover.theme.ThemeFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DiscoverFragment : Fragment(R.layout.fragment_discover) {
    private val binding by viewBinding<FragmentDiscoverBinding>()

    private val viewPager by lazy { binding.viewPager }
    private val tabLayout by lazy { binding.tabLayout }

    val fragments = listOf(
            "Pour toi" to ForYouFragment(),
            "Populaire" to PopularFragment(),
            "Direct" to DirectFragment(),
            "Thèmes" to ThemeFragment()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPager()
    }

    private fun setUpPager() {

        viewPager.adapter = FragmentPagerAdapter(requireActivity(),fragments)
        viewPager.currentItem = 0

        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position ->
            tab.text = fragments[position].first
            viewPager.currentItem = 0
        }.attach()
    }
}