package com.dondja.dondja.ui.fragment.feed.feedall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.dondja.dondja.*
import com.dondja.dondja.databinding.FragmentFeedAllBinding
import com.dondja.dondja.util.withModelsFrom


class FeedAllFragment : Fragment(R.layout.fragment_feed_all) {
    private val binding by viewBinding<FragmentFeedAllBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvFeedAll.withModels {
            carousel {
                id("story")
                withModelsFrom(listOf(1,2,3,4,5)) {
                    StoryBindingModel_()
                            .id(it)
                            .onStoryClick { _ ->
                                findNavController().navigate(R.id.to_storyViewFragment)
                            }
                }
            }
            for (i in 1..6) {
                post {
                    id("post")
                }

                if(i == 3) {
                    carousel {
                        id(i)
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