package com.dondja.dondja.ui.fragment.feed.feedall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.carousel
import com.dondja.dondja.*
import com.dondja.dondja.databinding.FragmentFeedAllBinding
import com.dondja.dondja.ui.fragment.feed.FeedFragmentDirections
import com.dondja.dondja.util.ViewState.*
import com.dondja.dondja.util.showToast
import com.dondja.dondja.util.ui.withModelsFrom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedAllFragment : Fragment(R.layout.fragment_feed_all) {
    private val binding by viewBinding<FragmentFeedAllBinding>()
    private val viewModel by viewModels<FeedAllViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDataFromViewModel()
    }

    private fun observeDataFromViewModel() {
        viewModel.data.observe(viewLifecycleOwner) {
            when(it) {
                is Loading -> {

                }
                is Success -> bindData(it.data)
                is Failure -> {
                    showToast(it.error.message.toString())
                }
            }
        }
    }

    private fun bindData(data: FeedAllData) {
        binding.rvFeedAll.withModels {
            headerStoryCarousel {
                id("stories-header")
            }
            carousel {
                id("story")
                withModelsFrom(data.stories) {
                    StoryBindingModel_()
                            .id(it.uid)
                            .onStoryClick { _ ->
                                findNavController().navigate(R.id.to_storyViewFragment)
                            }
                }
            }
            for (item in data.posts.withIndex()) {
                post {
                    id(item.value.uid)
                    onThemeClick { _ ->
                        val direction = FeedFragmentDirections.toThemeFeedFragment()
                        findNavController().navigate(direction)
                    }
                    onPostClick { _ ->
                        val direction = FeedFragmentDirections.toPostViewFragment()
                        findNavController().navigate(direction)
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
}