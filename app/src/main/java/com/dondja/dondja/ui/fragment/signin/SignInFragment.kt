package com.dondja.dondja.ui.fragment.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.Result.*
import com.dondja.dondja.databinding.FragmentSignInBinding
import com.dondja.dondja.ui.activity.MainActivity
import com.dondja.dondja.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val binding by viewBinding<FragmentSignInBinding>()
    private val viewModel by viewModels<SignInViewModel>()
    private var alreadyToasted = false

    var phonePattern = "[0-9]{10}"
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                validateAndLogin()
                alreadyToasted = false
            }
        }
    }

    private fun validateAndLogin() {
        val emailOrPhone = binding.emailAndPhone.text.toString()
        val password = binding.password.text.toString()

        val isPhone = emailOrPhone.matches(phonePattern.toRegex())
        val isEmail = emailOrPhone.matches(emailPattern.toRegex())

        if (isEmail) {
            viewModel.signInWithEmail(emailOrPhone,password)
        } else {
            viewModel.signInWithPhoneNumber(emailOrPhone,password)
        }
        observeLoginState()
    }

    private fun observeLoginState() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            binding.btnLogin.isEnabled = it !is Loading
            when(it) {
                is Success -> {
                    startActivity(Intent(requireContext(),MainActivity::class.java))
                }

                is Error -> {
                    if (!alreadyToasted) {
                        showToast(it.exception.message.toString())
                        alreadyToasted = true
                    }
                }
            }
        }
    }
}