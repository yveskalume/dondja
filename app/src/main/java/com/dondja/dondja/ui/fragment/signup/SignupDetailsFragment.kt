package com.dondja.dondja.ui.fragment.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignupDetailsBinding
import com.dondja.dondja.ui.activity.MainActivity
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupDetailsFragment : Fragment(R.layout.fragment_signup_details) {
    private val viewModel by activityViewModels<AuthViewModel>()
    private val binding by viewBinding<FragmentSignupDetailsBinding>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }
    }
}