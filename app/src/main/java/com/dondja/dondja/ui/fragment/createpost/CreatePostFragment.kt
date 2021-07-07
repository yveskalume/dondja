package com.dondja.dondja.ui.fragment.createpost

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.dondja.dondja.R
import com.dondja.dondja.data.entity.Theme
import com.dondja.dondja.data.util.Result.*
import com.dondja.dondja.databinding.FragmentCreatePostBinding
import com.dondja.dondja.imageCreatePost
import com.dondja.dondja.util.Log
import com.dondja.dondja.util.getImageFile
import com.dondja.dondja.util.showToast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.firebase.auth.FirebaseAuth
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
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
        setUpThemesInput()
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
                    userProfilePicture = currentUser!!.photoUrl.toString(),
                    createdAt = Date(System.currentTimeMillis())

                )
//                viewModel.publishPost()
            }

            setUpTextListnerForThemes()

        }
    }

    private fun setUpTextListnerForThemes() {
        binding.edTheme.addTextChangedListener { editable ->
            val trimmed = editable.toString().trim { it <= ' ' }
            if (trimmed.length > 1 && trimmed.endsWith(",")) {

                val chip = Chip(this.context).apply {
                    text = trimmed.substring(0, trimmed.length - 1)
                    chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red))
                    setTextColor(resources.getColor(R.color.white))
                }

                binding.themeChipGroup.addView(chip)
                editable?.clear()
            }
        }

        binding.edTheme.setOnKeyListener { _, _, event ->
            if (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_DEL) {
                if (binding.edTheme.length() == 0 && binding.themeChipGroup.childCount > 0) {
                    val chip = binding.themeChipGroup.getChildAt(binding.themeChipGroup.childCount - 1) as Chip
                    binding.themeChipGroup.removeView(chip)
                }
            }
            false
        }
    }

    private fun setUpThemesInput() {
        viewModel.themes.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {

                }
                is Success -> bindThemesToInput(it.data)
                is Error -> Toast.makeText(requireContext(), "Erreur", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindThemesToInput(data: List<Theme>) {
        val themeAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,data)
        binding.edTheme.run {
            setAdapter(themeAdapter)
            setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
            threshold = 1
            setOnItemClickListener { parent, view, position, id ->
                val theme = themeAdapter.getItem(position)
                createThemeChipInEditText(theme)
            }
        }
    }

    private fun createThemeChipInEditText(theme: Theme?) {
        if (theme == null) return
//        val cursorPosition = binding.edTheme.selectionStart
//        val spanLength = theme.title.length + 2
//        val editableText = binding.edTheme.text
//        val chip = Chip(this.context).apply {
//            text = theme.title
//            chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red))
//            setTextColor(resources.getColor(R.color.black))
//        }
//
//        val chipDrawable = chip.chipDrawable
//        chipDrawable.setBounds(0, 0, chipDrawable.intrinsicWidth, chipDrawable.intrinsicHeight)
//
//        val span = ImageSpan(chip.chipDrawable)
//        editableText.setSpan(chipDrawable, cursorPosition - spanLength, cursorPosition, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
//        binding.edTheme.setText(theme.title)
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