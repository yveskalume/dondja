package com.dondja.dondja.ui.fragment.discover.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentPopularBinding
import com.dondja.dondja.discover
import com.dondja.dondja.util.ui.GridColumn

class PopularFragment : Fragment(R.layout.fragment_popular) {
    private val binding by viewBinding<FragmentPopularBinding>()
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