package com.dondja.dondja.ui.fragment.feed.feedall

import com.dondja.dondja.data.interactor.PostInteractor
import com.dondja.dondja.data.interactor.StoryInteractor
import com.dondja.dondja.data.util.data
import com.dondja.dondja.data.util.succeeded
import com.dondja.dondja.data.util.successOr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedAllPresenter @Inject constructor(
    private val postInteractor: PostInteractor,
    private val storyInteractor: StoryInteractor
) {

    suspend fun getAllPostFromFollowing() = withContext(Dispatchers.IO) {
        postInteractor.getAllFromFlowing().map { it.data!! }
    }

    suspend fun getAllStoryFromFollowing() = withContext(Dispatchers.IO) {
        storyInteractor.getAllFromFlowing().map { it.data!! }
    }
}