package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignupOtpBinding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupOtpFragment : Fragment(R.layout.fragment_signup_otp) {
    private val binding by viewBinding<FragmentSignupOtpBinding>()
    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnNext.setOnClickListener {
            val directions = SignupOtpFragmentDirections.toSignupProfilePictureFragment()
            findNavController().navigate(directions)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}