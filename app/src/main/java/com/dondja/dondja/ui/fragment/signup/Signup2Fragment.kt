package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignup2Binding


class Signup2Fragment : Fragment(R.layout.fragment_signup2) {
    private val binding by viewBinding<FragmentSignup2Binding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.to_signup3Fragment)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}