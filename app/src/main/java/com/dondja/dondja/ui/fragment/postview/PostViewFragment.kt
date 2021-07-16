package com.dondja.dondja.ui.fragment.postview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.dondja.dondja.R
import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.databinding.FragmentPostViewBinding
import com.dondja.dondja.util.showToast
import com.dondja.dondja.util.ui.bindDate
import com.dondja.dondja.util.ui.setImageUrl
import com.dondja.dondja.util.ui.setImagesWithourDot

class PostViewFragment : Fragment(R.layout.fragment_post_view), MavericksView {
    private val viewModel : PostViewViewModel by fragmentViewModel()
    private val binding by viewBinding<FragmentPostViewBinding>()
    private val args by navArgs<PostViewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListenr()
    }

    private fun setUpListenr() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun bindData(post: Post) {
        binding.run {
            txtCommentsNumber.text = post.commentsNumber.toString()
            txtlikes.text = post.likes.toString()
            txtShares.text = post.shares.toString()
            description.text = post.description
            postImageView.setImagesWithourDot(post.imagesUrls)
            profile.setImageUrl(post.userProfilePicture)
            materialTextView3.bindDate(post.createdAt!!)

        }

        binding.likeBtn.setOnClickListener {
            viewModel.likeOrDislikePost(post)
        }
    }

    override fun invalidate() = withState(viewModel) {

        when(it.post) {
            is Loading -> {

            }

            is Success -> {
                bindData(it.post.invoke())
            }

            is Fail -> {
                showToast(it.post.error.message.toString())
            }
        }
    }
}