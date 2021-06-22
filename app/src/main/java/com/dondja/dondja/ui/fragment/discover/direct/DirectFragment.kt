package com.dondja.dondja.ui.fragment.discover.direct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.dondja.dondja.DiscoverForCarouselBindingModel_
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentDirectBinding
import com.dondja.dondja.discover
import com.dondja.dondja.headerDiscoverDirect
import com.dondja.dondja.util.ui.GridColumn

class DirectFragment : Fragment(R.layout.fragment_direct) {
    private val binding by viewBinding<FragmentDirectBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {

        val items = arrayListOf<DiscoverForCarouselBindingModel_>()

        for (i in 1..4) {
            items.add(
                    DiscoverForCarouselBindingModel_()
                            .id(i)
            )
        }

        binding.rV.withModels {
            val columns = GridColumn.calculateNoOfColumns(requireContext(),200F)
            binding.rV.layoutManager = GridLayoutManager(context,columns)
            for (i in 1..6) {
                headerDiscoverDirect {
                    id(i)
                }
                discover {
                    id(i)
                }
            }
        }
    }
}