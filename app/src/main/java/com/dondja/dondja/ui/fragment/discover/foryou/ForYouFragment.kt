package com.dondja.dondja.ui.fragment.discover.foryou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentForYouBinding
import com.dondja.dondja.discover
import com.dondja.dondja.util.GridColumn


class ForYouFragment : Fragment(R.layout.fragment_for_you) {

    private val binding by viewBinding<FragmentForYouBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        val columns = GridColumn.calculateNoOfColumns(requireContext(),200F)
        binding.rV.layoutManager = GridLayoutManager(context,columns)
        binding.rV.withModels {
            for (i in 1..16) {
                discover {
                    id(i)
                }
            }
        }
    }
}