package com.dondja.dondja.ui.fragment.feed.feedall

import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.interactor.PostInteractor
import com.dondja.dondja.data.interactor.StoryInteractor
import com.dondja.dondja.data.util.data
import com.dondja.dondja.data.util.succeeded
import com.dondja.dondja.data.util.successOr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedAllPresenter @Inject constructor(
    private val postInteractor: PostInteractor,
    private val storyInteractor: StoryInteractor
) {

    @ExperimentalCoroutinesApi
    suspend fun getAllPostFromFollowing() = withContext(Dispatchers.IO) {
        postInteractor.getAllFromFlowing()
    }

    @ExperimentalCoroutinesApi
    suspend fun getAllStoryFromFollowing() = withContext(Dispatchers.IO) {
        storyInteractor.getAllFromFlowing()
    }

    suspend fun likeOrDislikePost(post: Post) = withContext(Dispatchers.IO) {
        postInteractor.likeOrDislike(post)
    }
}