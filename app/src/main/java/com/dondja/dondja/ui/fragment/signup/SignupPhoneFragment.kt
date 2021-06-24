package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignupPhoneBinding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupPhoneFragment : Fragment(R.layout.fragment_signup_phone) {
    private val binding by viewBinding<FragmentSignupPhoneBinding>()

    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpForm()
        setUpListener()
    }

    private fun setUpForm() {
        form {
            inputLayout(binding.emailLayout) {
                isEmail()
            }

            inputLayout(binding.passwordLayout) {
                isNotEmpty()
                length().atLeast(6)
            }
            inputLayout(binding.passwordConfirmLayout) {
                isNotEmpty()
                length().atLeast(6)
            }

            submitWith(binding.btnNext) {
                bindToViewModel()
            }

            binding.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setUpListener() {
        binding.txtUseEmail.setOnClickListener {
            findNavController().navigate(SignupPhoneFragmentDirections.toSignupEmailFragment())
        }
    }

    private fun bindToViewModel() {
        if (binding.password.text.toString() != binding.passwordConfirm.text.toString()) {
            binding.passwordConfirmLayout.error = "Mot de passe ne correspond pas"
            binding.passwordLayout.error = "Mot de passe ne correspond pas"
            return
        }
        viewModel.user = viewModel.user.copy(
            email = binding.email.text.toString(),
            password = binding.password.text.toString(),
        )
        val directions = SignupPhoneFragmentDirections.toSignupOtpFragment()
        findNavController().navigate(directions)
    }
}