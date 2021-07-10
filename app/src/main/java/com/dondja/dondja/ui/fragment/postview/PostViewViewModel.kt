package com.dondja.dondja.ui.fragment.postview

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.dondja.dondja.app.di.mavericks.AssistedViewModelFactory
import com.dondja.dondja.app.di.mavericks.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class PostViewViewModel @AssistedInject constructor(
    @Assisted state: PostViewState
) : MavericksViewModel<PostViewState>(state) {




    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PostViewViewModel, PostViewState> {
        override fun create(state: PostViewState): PostViewViewModel
    }

    companion object :
        MavericksViewModelFactory<PostViewViewModel, PostViewState> by hiltMavericksViewModelFactory()
}