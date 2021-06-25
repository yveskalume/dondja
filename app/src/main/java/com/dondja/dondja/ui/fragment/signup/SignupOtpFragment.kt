package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.Result.*
import com.dondja.dondja.databinding.FragmentSignupOtpBinding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import com.dondja.dondja.util.showToast
import com.google.android.gms.safetynet.SafetyNet
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SignupOtpFragment : Fragment(R.layout.fragment_signup_otp) {
    private val binding by viewBinding<FragmentSignupOtpBinding>()
    private val viewModel by activityViewModels<AuthViewModel>()
    private val auth by lazy { FirebaseAuth.getInstance() }

    lateinit var storedVerificationId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        signUpWithPhoneNumber()
        auth.setLanguageCode("fr")
    }

    private fun setUpListener() {
        binding.btnNext.setOnClickListener {
            val code = binding.code.text.toString()
            if (code.isEmpty()) {
                showToast("Veuillez saisir le code")
                return@setOnClickListener
            }
            val credential = PhoneAuthProvider.getCredential(storedVerificationId,code)
            viewModel.signUpWithPhoneNumber(credential)
            observeSignupState()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeSignupState() {
        viewModel.signUpWithPhoneFlowState.observe(viewLifecycleOwner) {
            binding.btnNext.isEnabled = it is Loading
            when(it) {
                is Success -> {
                    val directions = SignupOtpFragmentDirections.toSignupProfilePictureFragment()
                    findNavController().navigate(directions)
                }
                is Error -> {
                    showToast(it.exception.message.toString())
                }
            }
        }
    }

    private fun signUpWithPhoneNumber() {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(viewModel.user.phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            viewModel.signUpWithPhoneNumber(p0)
            observeSignupState()
        }

        override fun onVerificationFailed(p0: FirebaseException) {

        }

        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, p1)
            storedVerificationId = verificationId
//            resendToken = token
        }

    }

}