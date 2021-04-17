package com.dondja.dondja.ui.fragment.storyview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentStoryViewBinding
import jp.shts.android.storiesprogressview.StoriesProgressView

class StoryViewFragment : Fragment(R.layout.fragment_story_view) {

    private var counter = 0

    private val binding by viewBinding<FragmentStoryViewBinding>()
    private val storyView by lazy {
        binding.storyView
    }

    private val imageView by lazy {
        binding.imageView
    }

    val images = arrayListOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpStoryView()
    }

    private fun setUpStoryView() {
        storyView.setStoriesCount(2)
        storyView.setStoryDuration(3000L)

        imageView.setImageResource(images[counter])

        setUpListener()
    }

    private fun setUpListener() {

        storyView.setStoriesListener(object : StoriesProgressView.StoriesListener{
            override fun onNext() {
                imageView.setImageResource(images[++counter])
            }

            override fun onPrev() {
                imageView.setImageResource(images[--counter])
            }

            override fun onComplete() {
                findNavController().navigateUp()
            }

        })

        storyView.startStories()

        imageView.setOnClickListener {
            storyView.skip()
        }

        imageView.setOnLongClickListener {
            storyView.pause()

            true
        }
    }
}