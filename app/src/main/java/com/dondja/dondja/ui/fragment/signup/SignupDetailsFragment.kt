package com.dondja.dondja.ui.fragment.signup

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dondja.dondja.R
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupDetailsFragment : Fragment(R.layout.fragment_signup_details) {
    private val viewModel by activityViewModels<AuthViewModel>()
}