package com.dondja.dondja.ui.fragment.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentSignup4Binding
import com.dondja.dondja.util.Log
import com.dondja.dondja.util.getImageFile
import com.dondja.dondja.util.setImageUrl
import com.dondja.dondja.util.showToast
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity

class Signup4Fragment : Fragment(R.layout.fragment_signup4) {
    private val binding by viewBinding<FragmentSignup4Binding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnNext.setOnClickListener {
            val direction = Signup4FragmentDirections.toChooseThemeFragment()
            findNavController().navigate(direction)
        }

        binding.btnChoosePic.setOnClickListener {
            pickImage()
        }
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

    private fun bindInfo(url: String) {
        Log("imageUrl : $url")
        binding.profileImg.setImageUrl(url)
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
                bindInfo(UCrop.getOutput(data!!).toString())
            } else if (resultCode == UCrop.RESULT_ERROR ) {
                showToast("Erreur")
            }
        }
    }
}