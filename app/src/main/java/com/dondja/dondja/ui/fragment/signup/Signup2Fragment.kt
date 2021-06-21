package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignup2Binding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import com.dondja.dondja.util.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Signup2Fragment : Fragment(R.layout.fragment_signup2) {
    private val binding by viewBinding<FragmentSignup2Binding>()
    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpForm()
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

    private fun bindToViewModel() {
//        if (binding.password.editableText != binding.passwordConfirm.editableText) {
//            binding.passwordConfirmLayout.error = "Mot de passe ne correspond pas"
//            binding.passwordLayout.error = "Mot de passe ne correspond pas"
//            return
//        }
        viewModel.user = viewModel.user.copy(
            email = binding.email.text.toString(),
            password = binding.password.text.toString(),
        )
        val directions = Signup2FragmentDirections.toSignup3Fragment()
        findNavController().navigate(directions)
    }
}