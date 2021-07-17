package com.dondja.dondja.ui.fragment.feed.feedall

import androidx.fragment.app.Fragment
import android.viewbinding.library.fragment.viewBinding
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.carousel
import com.airbnb.mvrx.*
import com.dondja.dondja.*
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentFeedAllBinding
import com.dondja.dondja.ui.fragment.feed.FeedFragmentDirections
import com.dondja.dondja.util.showToast
import com.dondja.dondja.util.ui.ThemeClickListener
import com.dondja.dondja.util.ui.withModelsFrom
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedAllFragment : Fragment(R.layout.fragment_feed_all),MavericksView, ThemeClickListener {
    private val binding by viewBinding<FragmentFeedAllBinding>()
    private val viewModel: FeedAllViewModel by fragmentViewModel()

    private val currentUser by lazy { FirebaseAuth.getInstance().currentUser }

    override fun onThemeClick(themeName: String) {
//        val direction = FeedFragmentDirections.toThemeFeedFragment()
//        findNavController().navigate(direction)
    }

    private fun bindData(data: FeedAllData) {
        binding.rvFeedAll.withModels {
            headerStoryCarousel {
                id("stories-header")
            }
//            carousel {
//                id("story")
//                withModelsFrom(data.stories) {
//                    StoryBindingModel_()
//                            .id(it.uid)
//                            .onStoryClick { _ ->
//                                findNavController().navigate(R.id.to_storyViewFragment)
//                            }
//                }
//            }
            for (item in data.posts.withIndex()) {
                post {
                    id(item.value.uid)
                    post(item.value)
                    isLiked(item.value.isLiked(currentUser!!.uid))
                    themeClickListener(this@FeedAllFragment)
                    onTextClick { _ ->
                        val direction = FeedFragmentDirections.toPostViewFragment(item.value)
                        findNavController().navigate(direction)
                    }
                    onPostClick {
                        val direction = FeedFragmentDirections.toPostViewFragment(item.value)
                        findNavController().navigate(direction)
                    }

                    likeClickListener { _ ->
                        viewModel.likeOrDislikePost(item.value)
                        binding.rvFeedAll.requestModelBuild()
                    }
                }

                if(item.index == 3) {
                    carousel {
                        id("sugestions-carousel")
                        withModelsFrom(listOf(1,2,3,4,5)) {
                            UserSugestionBindingModel_()
                                    .id(it)
                        }
                    }
                } else {
                    postSeparator {
                        id("separator")
                    }
                }

            }
        }
    }

    override fun invalidate() = withState(viewModel) {
        when(it.data) {
            is Loading -> {

            }

            is Success -> {
                bindData(it.data.invoke())
            }

            is Fail -> {
                showToast(it.data.error.message.toString())
            }
        }
    }
}