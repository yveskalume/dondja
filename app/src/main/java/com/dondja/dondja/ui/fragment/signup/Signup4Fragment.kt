package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignup4Binding

class Signup4Fragment : Fragment(R.layout.fragment_signup4) {
    private val binding by viewBinding<FragmentSignup4Binding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {

    }
}