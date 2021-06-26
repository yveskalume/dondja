package com.dondja.dondja.ui.fragment.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignInBinding
import com.dondja.dondja.ui.activity.MainActivity

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val binding by viewBinding<FragmentSignInBinding>()
    private val viewModel by viewModels<SignInViewModel>()

    var phonePattern = "[0-9]{10}"
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        setUpForm()
    }

    private fun setUpForm() {
        form {
            inputLayout(binding.emailAndPhoneLayout) {
                isNotEmpty()
            }

            inputLayout(binding.passwordLayout) {
                isNotEmpty()
            }

            submitWith(binding.btnLogin) {
                checkPatterns()
            }
        }
    }

    private fun checkPatterns() {
        val emailOrPhone = binding.emailAndPhone.text.toString()
        val password = binding.password.text.toString()

        val isPhone = emailOrPhone.matches(phonePattern.toRegex())
        val isEmail = emailOrPhone.matches(emailPattern.toRegex())

        if (isEmail) {
            viewModel.signInWithEmail(emailOrPhone,password)
        } else {
            viewModel.
        }
    }

    private fun setUpListener() {
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }
    }
}