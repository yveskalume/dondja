package com.dondja.dondja.ui.fragment.discover.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.mvrx.*
import com.dondja.dondja.R
import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.databinding.FragmentPopularBinding
import com.dondja.dondja.discover
import com.dondja.dondja.util.showToast
import com.dondja.dondja.util.ui.GridColumn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment(R.layout.fragment_popular), MavericksView {
    private val binding by viewBinding<FragmentPopularBinding>()
    private val viewModel: PopularViewModel by fragmentViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun bindData(posts: List<Post>) {
        val columns = GridColumn.calculateNoOfColumns(requireContext(),200F)
        binding.rV.layoutManager = GridLayoutManager(context,columns)
        binding.rV.withModels {
            for (post in posts) {
                discover {
                    id(post.uid)
                    post(post)
                    onImageClickListener {_ ->
                        val directions = PopularFragmentDirections.toPostViewFragment(post)
                        findNavController().navigate(directions)
                    }

                    onProfileClickListener {_ ->

                    }
                }
            }
        }
    }

    override fun invalidate() = withState(viewModel) {
        when(it.posts) {
            is Loading -> {

            }
            is Success -> bindData(it.posts.invoke())
            is Fail -> {
                showToast(it.posts.error.message.toString())
            }
        }
    }
}