package com.dondja.dondja.ui.fragment.choosetheme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentChooseThemeBinding
import com.dondja.dondja.ui.activity.MainActivity

class ChooseThemeFragment : Fragment(R.layout.fragment_choose_theme) {
    private val binding by viewBinding<FragmentChooseThemeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }
    }
}