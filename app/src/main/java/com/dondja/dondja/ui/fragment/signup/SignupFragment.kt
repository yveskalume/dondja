package com.dondja.dondja.ui.fragment.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.viewbinding.library.fragment.viewBinding
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignupBinding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import com.dondja.dondja.util.getFormattedDate
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SignupFragment : Fragment(R.layout.fragment_signup) {

    private val viewModel by activityViewModels<AuthViewModel>()
    private val binding by viewBinding<FragmentSignupBinding>()
    private val birthdayLiveData = MutableLiveData<Date>()
    private val sexeAdapter by lazy {
        ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line, listOf("Masculin", "Feminin"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpForm()
    }

    private fun setUpForm() {
        form {

            inputLayout(binding.firstnameLayout) {
                isNotEmpty()
            }

            inputLayout(binding.secondnameLayout) {
                isNotEmpty()
            }

            inputLayout(binding.sexeLayout) {
                isNotEmpty()
            }

            inputLayout(binding.bithLayout) {
                isNotEmpty()
            }

            binding.birthday.setOnClickListener {
                showDatePicker()
            }


            birthdayLiveData.observe(viewLifecycleOwner) {
                binding.birthday.setText(it?.getFormattedDate("dd MMM yyyy"))
                viewModel.user = viewModel.user.copy(birthday = it)
            }

            binding.sexe.setAdapter(sexeAdapter)

            submitWith(binding.btnNext) {
                bindDataToUserInViewModel()
                val direction = SignupFragmentDirections.toSignupEmailFragment()
                findNavController().navigate(direction)
            }

            binding.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun bindDataToUserInViewModel() {
        viewModel.user = viewModel.user.copy(
            firstName = binding.firstname.text.toString(),
            secondName = binding.secondname.text.toString(),
            sexe = binding.sexe.text.toString(),
            birthday = birthdayLiveData.value,
        )
    }

    private fun showDatePicker() {
        MaterialDialog(requireContext()).show {
            datePicker(maxDate = Calendar.getInstance()) { dialog , datetime ->
                birthdayLiveData.value = datetime.time
                dialog.dismiss()
            }
        }
    }
}