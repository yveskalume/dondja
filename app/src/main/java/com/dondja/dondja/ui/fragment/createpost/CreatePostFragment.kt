package com.dondja.dondja.ui.fragment.createpost

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.Result.*
import com.dondja.dondja.databinding.FragmentCreatePostBinding
import com.dondja.dondja.imageCreatePost
import com.dondja.dondja.util.Log
import com.dondja.dondja.util.getImageFile
import com.dondja.dondja.util.showToast
import com.google.firebase.auth.FirebaseAuth
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePostFragment : Fragment(R.layout.fragment_create_post) {
    private val binding by viewBinding<FragmentCreatePostBinding>()

    private val viewModel by viewModels<CreatePostViewModel>()
    private val currentUser by lazy { FirebaseAuth.getInstance().currentUser }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        viewModel.imageUrls.observe(viewLifecycleOwner, {
            setUpImageRv(it)
            Log(it.toString())
        })

        setUpForm()
        observePublishingState()
    }

    private fun observePublishingState() {
        viewModel.publishingState.observe(viewLifecycleOwner) {
            binding.btnPublish.isEnabled = it !is Loading

            when(it) {
                is Success -> findNavController().navigateUp()
                is Error -> {
                    showToast(it.exception.message.toString())
                }
            }
        }
    }

    private fun setUpForm() {
        form {
//            inputLayout(binding.textInputLayout) {
//                isNotEmpty()
//            }
//            inputLayout(binding.textInputLayout2) {
//                isNotEmpty()
//            }
//            inputLayout(binding.textInputLayout3) {
//                isNotEmpty()
//            }
//            inputLayout(binding.textInputLayout4) {
//                isNotEmpty()
//            }

            submitWith(binding.btnPublish) {
                viewModel.post = viewModel.post.copy(
                    userUid = currentUser!!.uid,
                    title = binding.edTitle.text.toString(),
                    description = binding.edDescription.text.toString(),
                    userDisplayName = currentUser!!.displayName!!,
                    userProfilePicture = currentUser!!.photoUrl.toString()

                )
                viewModel.publishPost()
            }

        }
    }

    private fun setUpListener() {
        binding.txtAddMedia.setOnClickListener {
            pickImage()
        }
    }



    private fun setUpImageRv(img: List<String>) {
        Log(img.toString())
        binding.rVimg.withModels {
            for (item in img) {
                imageCreatePost {
                    id(item)
                    imageUrl(item)
                }
            }
        }
    }


    private fun pickImage() {
        val photoIntent = Intent().apply {
            action = Intent.ACTION_GET_CONTENT
            type = "image/*"
        }

        if (photoIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(
                photoIntent, IMG_CODE
            )
        }
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
        if (requestCode == IMG_CODE) {
            getImageInfo(resultCode , data)
        }

        if (requestCode == UCrop.REQUEST_CROP) {
            if (resultCode == UCropActivity.RESULT_OK) {
                viewModel.bindImageUri(UCrop.getOutput(data!!))
            } else if (resultCode == UCrop.RESULT_ERROR ) {
                showToast("Erreur")
            }
        }
    }

    companion object RequestCode {
        const val IMG_CODE = 0
    }
}