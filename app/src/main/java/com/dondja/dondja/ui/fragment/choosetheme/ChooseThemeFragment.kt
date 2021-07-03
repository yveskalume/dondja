package com.dondja.dondja.ui.fragment.choosetheme

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.viewbinding.library.fragment.viewBinding
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.data.entity.Theme
import com.dondja.dondja.data.util.Result.*
import com.dondja.dondja.databinding.FragmentChooseThemeBinding
import com.dondja.dondja.util.showToast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseThemeFragment : Fragment(R.layout.fragment_choose_theme) {
    private val binding by viewBinding<FragmentChooseThemeBinding>()
    private val viewModel by viewModels<ChooseThemeViewModel>()

    private val choosenTags: ArrayList<String> = arrayListOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        observerThemeFromViewModel()
    }

    private fun setUpListener() {
        binding.btnNext.setOnClickListener {
            submitThemes()
        }
    }

    private fun observerThemeFromViewModel() {
        viewModel.themes.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {
                }
                is Success -> {
                    bindThemes(it.data)
                }
                is Error -> {
                    showToast(it.exception.message.toString())
                }
            }
        }
    }

    private fun submitThemes() {
        viewModel.setFollowingThemes(choosenTags)
        viewModel.submittingThemesState.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {
            }
                is Success -> {
                    val directions = ChooseThemeFragmentDirections.toSignupDetailsFragment()
                    findNavController().navigate(directions)
            }
                is Error -> {
                showToast(it.exception.message.toString())
            }
            }
        }
    }

    private fun bindThemes(themes: List<Theme>) {
        for (theme in themes) {
            val chip = Chip(this.context).apply {
                text = theme.title
                isCheckable = true
                isClickable = true
                chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context,R.color.chip_state_background_color))
                setChipDrawable(ChipDrawable.createFromAttributes(requireContext(),null,0,R.style.ThemeChipStyle))

                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        choosenTags.add(theme.title)
                        showToast("Checked")
                    } else {
                        choosenTags.remove(theme.title)
                        showToast("unChecked")
                    }
                }
            }
            binding.themesGroupe.addView(chip)
        }
    }
}