package com.dondja.dondja.ui.fragment.feed.feedall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.dondja.dondja.*
import com.dondja.dondja.databinding.FragmentFeedAllBinding


class FeedAllFragment : Fragment(R.layout.fragment_feed_all) {
    private val binding by viewBinding<FragmentFeedAllBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val stories: ArrayList<StoryBindingModel_> = arrayListOf()
        val sugestions: ArrayList<UserSugestionBindingModel_> = arrayListOf()
        for (i in 1..6) {
            val model = StoryBindingModel_()
                    .id(i)
            stories.add(model)

            UserSugestionBindingModel_()
                    .id(i).also {
                        sugestions.add(it)
                    }
        }
        binding.rvFeedAll.withModels {
            Carousel.setDefaultGlobalSnapHelperFactory(null)
            carousel {
                id("story")
                models(stories)
            }
            for (i in 1..6) {
                post {
                    id("post")
                }

                if(i == 4) {
                    carousel {
                        id(i)
                        models(sugestions)
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