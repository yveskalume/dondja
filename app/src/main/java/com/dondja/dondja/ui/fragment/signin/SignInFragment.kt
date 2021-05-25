package com.dondja.dondja.ui.fragment.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignInBinding
import com.dondja.dondja.ui.activity.MainActivity

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val binding by viewBinding<FragmentSignInBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }
    }
}