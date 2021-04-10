package com.dondja.dondja.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPagerAdapter (activity: FragmentActivity, private val fragments: List<Pair<String, Fragment>>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].second
    }
}