package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.Result.*
import com.dondja.dondja.databinding.FragmentSignupEmailBinding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import com.dondja.dondja.util.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignupEmailFragment : Fragment(R.layout.fragment_signup_email) {
    private val binding by viewBinding<FragmentSignupEmailBinding>()
    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpForm()
        setUpListener()
    }

    private fun setUpListener() {
        binding.txtUsePhone.setOnClickListener {
            findNavController().navigate(SignupEmailFragmentDirections.toSignupPhoneFragment())
        }
    }


    private fun showDialog() {
        MaterialDialog(requireContext()).show {
            message(text = "Pour nous assurer que l’e-mail ${binding.email.text} est bien le votre, nous allons vous envoyer un mail contenant un code de validations")
            negativeButton(text = "Modifier") {
                this.dismiss()
            }
            positiveButton(text = "Ok") {
                bindToViewModelAndSignUp()
            }
        }
    }

    private fun bindToViewModelAndSignUp() {
        viewModel.user = viewModel.user.copy(
            email = binding.email.text.toString(),
            password = binding.password.text.toString(),
        )
        viewModel.signUpWithEmailAndPassword()
        observeSignUpFlow()
    }

    private fun observeSignUpFlow() {
        viewModel.signUpFlowState.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {

                }
                is Success -> {
                    val directions = SignupEmailFragmentDirections.toSignupProfilePictureFragment()
                    findNavController().navigate(directions)
                }
                is Error -> {
                    showToast(it.exception.localizedMessage ?: it.exception.message.toString())
                }

            }
        }
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
                if (binding.password.text.toString() != binding.passwordConfirm.text.toString()) {
                    binding.passwordConfirmLayout.error = "Mot de passe ne correspond pas"
                    binding.passwordLayout.error = "Mot de passe ne correspond pas"
                    return@submitWith
                }
                showDialog()
            }

            binding.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}