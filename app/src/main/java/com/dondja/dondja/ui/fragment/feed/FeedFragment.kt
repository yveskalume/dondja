package com.dondja.dondja.ui.fragment.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentFeedBinding
import com.dondja.dondja.ui.adapter.FeedPagerAdapter
import com.dondja.dondja.ui.fragment.feed.feedall.FeedAllFragment
import com.dondja.dondja.ui.fragment.feed.feedfollowing.FeedFollowingFragment
import com.dondja.dondja.ui.fragment.feed.feedgroup.FeedGroupFragment
import com.dondja.dondja.ui.fragment.feed.feedhashtag.FeedHashtagFragment
import com.dondja.dondja.util.ui.setImageUrl
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding<FragmentFeedBinding>()
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPager()
        binding.profilePic.setImageUrl(auth.currentUser?.photoUrl.toString())
    }

    private fun setUpPager() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val fragments = listOf(
            "Tout" to FeedAllFragment(),
            "Groupes" to FeedGroupFragment(),
            "Following" to FeedFollowingFragment(),
            "Hashtag" to FeedHashtagFragment()
        )

        viewPager.adapter = FeedPagerAdapter(childFragmentManager,lifecycle,fragments)

        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position ->
            tab.text = fragments[position].first
            viewPager.setCurrentItem(tab.position,true)
        }.attach()

        tabLayout.getTabAt(0)?.let { tabLayout.selectTab(it) }
    }

}