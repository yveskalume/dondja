package com.dondja.dondja.ui.fragment.discover.direct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentDirectBinding
import com.dondja.dondja.discover

class DirectFragment : Fragment(R.layout.fragment_direct) {
    private val binding by viewBinding<FragmentDirectBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            for (i in 1..16) {
                discover {
                    id(i)
                }
            }
        }
    }
}