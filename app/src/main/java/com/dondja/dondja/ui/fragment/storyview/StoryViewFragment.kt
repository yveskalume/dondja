package com.dondja.dondja.ui.fragment.storyview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.dondja.dondja.R
import com.dondja.dondja.databinding.FragmentStoryViewBinding
import com.dondja.dondja.util.StoryView
import com.dondja.dondja.util.setHasStoryNavigationAction

class StoryViewFragment : Fragment(R.layout.fragment_story_view) {

    private var counter = 0
    private var longPressed: Boolean = false

    private val binding by viewBinding<FragmentStoryViewBinding>()
    private val storyView by lazy {
        binding.storyView
    }

    private val imageView by lazy {
        binding.imageView
    }

    val images = arrayListOf(
            R.drawable.img8,
            R.drawable.img2,
            R.drawable.img3)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpStoryView()
    }

    private fun setUpStoryView() {
        storyView.setStoriesCount(3)
        storyView.setStoryDuration(3000L)

        imageView.setImageResource(images[counter])

        setUpListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpListener() {

        storyView.setStoriesListener(object : StoryView.StoriesListener{
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

        binding.prevStory.setHasStoryNavigationAction(storyView) {
            storyView.reverse()
        }

        binding.nextStory.setHasStoryNavigationAction(storyView) {
            storyView.skip()
        }
    }
}