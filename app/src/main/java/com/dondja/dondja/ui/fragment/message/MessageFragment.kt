package com.dondja.dondja.ui.fragment.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentMessageBinding
import com.dondja.dondja.ui.adapter.FragmentPagerAdapter
import com.dondja.dondja.ui.fragment.discover.direct.DirectFragment
import com.dondja.dondja.ui.fragment.discover.foryou.ForYouFragment
import com.dondja.dondja.ui.fragment.discover.popular.PopularFragment
import com.dondja.dondja.ui.fragment.discover.theme.ThemeFragment
import com.dondja.dondja.ui.fragment.message.messageall.MessageAllFragment
import com.dondja.dondja.ui.fragment.message.messagefavorites.MessageFavoritesFragment
import com.dondja.dondja.ui.fragment.message.messagefriends.MessageFriendsFragment
import com.dondja.dondja.ui.fragment.message.messagegroupes.MessageGroupesFragment
import com.google.android.material.tabs.TabLayoutMediator

class MessageFragment : Fragment(R.layout.fragment_message) {

    private val binding by viewBinding<FragmentMessageBinding>()

    private val viewPager by lazy { binding.viewPager }
    private val tabLayout by lazy { binding.tabLayout }


    val fragments = listOf(
        "Tout" to MessageAllFragment(),
        "Amis" to MessageFriendsFragment(),
        "Groupes" to MessageGroupesFragment(),
        "Favoris" to MessageFavoritesFragment()
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