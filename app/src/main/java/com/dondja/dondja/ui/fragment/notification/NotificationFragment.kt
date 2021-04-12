package com.dondja.dondja.ui.fragment.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentNotificationBinding
import com.dondja.dondja.ui.adapter.FragmentPagerAdapter
import com.dondja.dondja.ui.fragment.notification.notificationall.NotificationAllFragment
import com.dondja.dondja.ui.fragment.notification.notificationfollowing.NotificationFollowingFragment
import com.dondja.dondja.ui.fragment.notification.notificationmention.NotificationMentionFragment
import com.google.android.material.tabs.TabLayoutMediator

class NotificationFragment : Fragment(R.layout.fragment_notification) {
    private val binding by viewBinding<FragmentNotificationBinding>()

    private val viewPager by lazy { binding.viewPager }
    private val tabLayout by lazy { binding.tabLayout }


    val fragments = listOf(
        "Tout" to NotificationAllFragment(),
        "Mentions" to NotificationMentionFragment(),
        "Abonnement" to NotificationFollowingFragment(),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPager()
    }

    private fun setUpPager() {

        viewPager.adapter = FragmentPagerAdapter(requireActivity(),fragments)

        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position ->
            tab.text = fragments[position].first
            viewPager.currentItem = 0
        }.attach()
    }
}