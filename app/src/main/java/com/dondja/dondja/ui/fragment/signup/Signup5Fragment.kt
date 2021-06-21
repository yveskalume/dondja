package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dondja.dondja.R
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Signup5Fragment : Fragment(R.layout.fragment_signup5) {
    private val viewModel by activityViewModels<AuthViewModel>()
}