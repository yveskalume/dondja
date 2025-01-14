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
import com.dondja.dondja.util.ui.setImageUrl
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupDetailsFragment : Fragment(R.layout.fragment_signup_details) {
    private val viewModel by activityViewModels<AuthViewModel>()
    private val binding by viewBinding<FragmentSignupDetailsBinding>()
    private val currentUser by lazy { FirebaseAuth.getInstance().currentUser }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.circleImageView12.setImageUrl(currentUser?.photoUrl.toString())
        binding.materialTextView27.text = resources.getString(R.string.bienvenue_sur_dondja_user,viewModel.user.firstName)
        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }
    }
}