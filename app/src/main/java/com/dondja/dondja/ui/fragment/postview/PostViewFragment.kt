package com.dondja.dondja.ui.fragment.postview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentPostViewBinding
import com.dondja.dondja.util.ui.setImageUrl

class PostViewFragment : Fragment(R.layout.fragment_post_view), MavericksView {
    private val viewModel : PostViewViewModel by fragmentViewModel()
    private val binding by viewBinding<FragmentPostViewBinding>()
    private val args by navArgs<PostViewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
        setUpListenr()
    }

    private fun setUpListenr() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun bindData() {
        binding.post =  args.post
    }

    override fun invalidate() {

    }
}