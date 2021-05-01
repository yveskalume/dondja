package com.dondja.dondja.ui.fragment.dialogstoryviewers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentDialogStoryViewersBinding
import com.dondja.dondja.statusViewer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DialogStoryViewersFragment : BottomSheetDialogFragment() {

    private val binding: FragmentDialogStoryViewersBinding by lazy { FragmentDialogStoryViewersBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        setupRv()
    }

    private fun setupRv() {
        binding.rV.withModels {
            for (i in 1..5) {
                statusViewer {
                    id(i)
                }
            }
        }
    }


    private fun setUpListener() {

    }
}