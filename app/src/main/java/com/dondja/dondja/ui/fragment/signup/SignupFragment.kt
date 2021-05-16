package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignupBinding

class SignupFragment : Fragment(R.layout.fragment_signup) {

    private val binding by viewBinding<FragmentSignupBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.to_signup2Fragment)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}