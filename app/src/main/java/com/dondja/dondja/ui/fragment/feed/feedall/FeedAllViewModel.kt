package com.dondja.dondja.ui.fragment.feed.feedall

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.dondja.dondja.app.di.mavericks.AssistedViewModelFactory
import com.dondja.dondja.app.di.mavericks.hiltMavericksViewModelFactory
import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.util.data
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FeedAllViewModel @AssistedInject constructor(
    @Assisted state: FeedAllViewState,
    private val presenter: FeedAllPresenter
) : MavericksViewModel<FeedAllViewState>(state) {

    init {
        getAllData()
    }

    private fun getAllData() = viewModelScope.launch {
        val postFlow = presenter.getAllPostFromFollowing().map { it.data!! }
        val storyFlow = presenter.getAllStoryFromFollowing().map { it.data!! }
        combine(postFlow,storyFlow) { posts, stories ->
            FeedAllData(posts,stories)
        }.execute {
            copy(data = it)
        }
    }

    fun likeOrDislikePost(post: Post) = viewModelScope.launch {
        presenter.likeOrDislikePost(post)
        getAllData()
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<FeedAllViewModel, FeedAllViewState> {
        override fun create(state: FeedAllViewState): FeedAllViewModel
    }

    companion object :
        MavericksViewModelFactory<FeedAllViewModel, FeedAllViewState> by hiltMavericksViewModelFactory()
}
