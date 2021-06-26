package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignupPhoneBinding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SignupPhoneFragment : Fragment(R.layout.fragment_signup_phone) {
    private val binding by viewBinding<FragmentSignupPhoneBinding>()

    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpForm()
        setUpListener()
    }


    private fun setUpListener() {
        binding.txtUseEmail.setOnClickListener {
            findNavController().navigate(SignupPhoneFragmentDirections.toSignupEmailFragment())
        }
    }

    private fun showDialog() {
        MaterialDialog(requireContext()).show {
            message(text = "Pour nous assurer que le numero ${binding.phone.text} est bien le votre, nous allons vous envoyer un mail contenant un code de validations")
            negativeButton(text = "Modifier") {
                this.dismiss()
            }
            positiveButton(text = "Ok") {
                bindToViewModel()
            }
        }
    }

    private fun bindToViewModel() {

        viewModel.user = viewModel.user.copy(
            phoneNumber = binding.phone.text.toString(),
            password = binding.password.text.toString(),
        )
        val directions = SignupPhoneFragmentDirections.toSignupOtpFragment()
        findNavController().navigate(directions)
    }

    private fun setUpForm() {
        form {
            inputLayout(binding.emailLayout) {
                isNumber()
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