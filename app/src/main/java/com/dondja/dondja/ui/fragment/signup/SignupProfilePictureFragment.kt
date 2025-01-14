package com.dondja.dondja.ui.fragment.signup

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.Result.*
import com.dondja.dondja.databinding.FragmentSignupProfilePictureBinding
import com.dondja.dondja.ui.activity.auth.AuthViewModel
import com.dondja.dondja.util.*
import com.dondja.dondja.util.ui.setImageUrl
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupProfilePictureFragment : Fragment(R.layout.fragment_signup_profile_picture) {
    private val binding by viewBinding<FragmentSignupProfilePictureBinding>()
    private val viewModel by activityViewModels<AuthViewModel>()
    private var alreadyToasted = false


    private var profileUri: Uri? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnChoosePic.setOnClickListener {
            pickImage()
        }

        binding.btnNext.setOnClickListener { btnNext ->
            alreadyToasted = false
            if (profileUri != null) {
                viewModel.uploadUserProfile(profileUri!!)
                viewModel.imageUploadingState.observe(viewLifecycleOwner) { result ->
                    btnNext.isEnabled = result !is Loading
                    when(result) {
                        is Success -> {
                            next()
                        }

                        is Error -> {
                            if(!alreadyToasted) {
                                showToast("Erreur")
                                alreadyToasted = true
                            }
                            Log(result.exception.toString())
                        }
                    }
                }
            } else {
                next()
            }
        }
    }

    private fun next() {
        val direction = SignupProfilePictureFragmentDirections.toChooseThemeFragment()
        findNavController().navigate(direction)
    }

    private fun pickImage() {
        val photoIntent = Intent().apply {
            action = Intent.ACTION_GET_CONTENT
            type = "image/*"
        }

        if (photoIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(
                photoIntent,0
            )
        }
    }

    private fun bindInfo(uri: Uri?) {
        Log("imageUrl : $uri")
        profileUri = uri
        binding.profileImg.setImageUrl(uri.toString())
    }

    private fun getImageInfo(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            val sourceUri = data.data ?: return
            val file = requireActivity().getImageFile()
            val destination = Uri.fromFile(file)

            UCrop.of(sourceUri,destination)
                .withAspectRatio(1.0.toFloat(), 1.0.toFloat())
                .start(requireActivity(),this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            getImageInfo(resultCode , data)
        }

        if (requestCode == UCrop.REQUEST_CROP) {
            if (resultCode == UCropActivity.RESULT_OK) {
                bindInfo(UCrop.getOutput(data!!))
            } else if (resultCode == UCrop.RESULT_ERROR ) {
                showToast("Erreur")
            }
        }
    }
}