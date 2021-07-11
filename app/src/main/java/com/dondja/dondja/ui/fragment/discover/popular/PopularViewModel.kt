package com.dondja.dondja.ui.fragment.discover.popular

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.dondja.dondja.app.di.mavericks.AssistedViewModelFactory
import com.dondja.dondja.app.di.mavericks.hiltMavericksViewModelFactory
import com.dondja.dondja.data.util.data
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PopularViewModel @AssistedInject constructor(
    @Assisted state: PopularViewState,
    private val presenter: PopularPresenter
) : MavericksViewModel<PopularViewState>(state) {

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        presenter.getPopularPosts().map {
            it.data!!
        }.execute {
            copy(posts = it )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<PopularViewModel, PopularViewState> {
        override fun create(state: PopularViewState): PopularViewModel
    }

    companion object :
        MavericksViewModelFactory<PopularViewModel, PopularViewState> by hiltMavericksViewModelFactory()
}