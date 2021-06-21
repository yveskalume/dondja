package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignup3Binding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Signup3Fragment : Fragment(R.layout.fragment_signup3) {
    private val binding by viewBinding<FragmentSignup3Binding>()
    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnNext.setOnClickListener {
            val directions = Signup3FragmentDirections.toSignup4Fragment()
            findNavController().navigate(directions)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}