package com.dondja.dondja.ui.fragment.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentWelcomeBinding
import com.dondja.dondja.ui.adapter.WelcomeTextPagerAdapter

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private val binding by viewBinding<FragmentWelcomeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        setUpPager()
    }

    private fun setUpPager() {
        val pager = binding.viewpager
        pager.adapter = WelcomeTextPagerAdapter()
        binding.dot.setViewPager2(pager)
    }

    private fun setUpListener() {

        binding.loginBtn.setOnClickListener {
            val direction = WelcomeFragmentDirections.toSignInFragment()
            findNavController().navigate(direction)
        }

        binding.btnSignUp.setOnClickListener {
            val direction = WelcomeFragmentDirections.toSignupFragment()
            findNavController().navigate(direction)
        }
    }
}