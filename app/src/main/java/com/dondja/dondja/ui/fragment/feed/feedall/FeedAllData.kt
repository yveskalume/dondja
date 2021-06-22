package com.dondja.dondja.ui.fragment.feed.feedall

import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.entity.Story

data class FeedAllData (
    val posts: List<Post>,
    val stories: List<Story>
)