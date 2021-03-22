package com.dondja.dondja.ui.fragment.feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentFeedBinding
import com.dondja.dondja.ui.adapter.FeedPagerAdapter
import com.dondja.dondja.ui.fragment.feedall.FeedAllFragment
import com.dondja.dondja.ui.fragment.feedfollowing.FeedFollowingFragment
import com.dondja.dondja.ui.fragment.feedgroup.FeedGroupFragment
import com.dondja.dondja.ui.fragment.feedhashtag.FeedHashtagFragment
import com.google.android.material.tabs.TabLayoutMediator

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding<FragmentFeedBinding>()

    private val viewPager by lazy { binding.viewPager }
    private val tabLayout by lazy { binding.tabLayout }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPager()
    }

    private fun setUpPager() {
        val fragments = listOf(
            "Tout" to FeedAllFragment(),
            "Groupes" to FeedGroupFragment(),
            "Following" to FeedFollowingFragment(),
            "Hashtag" to FeedHashtagFragment()
        )

       val feedPagerAdapter = FeedPagerAdapter(requireActivity(),fragments).also {
           viewPager.adapter = it
       }

        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position ->

//            viewPager.currentItem = position
            tab.text = fragments[position].first
            viewPager.currentItem = 0
            if (feedPagerAdapter.itemCount > 0) {
//                val currentItem = viewPager.currentItem
//                if (currentItem != tabLayout.selectedTabPosition) {
//                    tabLayout.getTabAt(currentItem)?.select()
//                }
            }
        }.attach()
    }

}