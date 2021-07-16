package com.dondja.dondja.ui.fragment.postview

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.dondja.dondja.app.di.mavericks.AssistedViewModelFactory
import com.dondja.dondja.app.di.mavericks.hiltMavericksViewModelFactory
import com.dondja.dondja.data.entity.Post
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class PostViewViewModel @AssistedInject constructor(
    @Assisted state: PostViewState,
    val presenter: PostViewPresenter
) : MavericksViewModel<PostViewState>(state) {

    fun likeOrDislikePost(post: Post) = viewModelScope.launch {
        presenter.likeOrDislikePost(post)
    }

    fun getPost(uidPost: String) = viewModelScope.launch {
        presenter.getPostByUid(uidPost)
    }


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PostViewViewModel, PostViewState> {
        override fun create(state: PostViewState): PostViewViewModel
    }

    companion object :
        MavericksViewModelFactory<PostViewViewModel, PostViewState> by hiltMavericksViewModelFactory()
}