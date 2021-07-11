package com.dondja.dondja.ui.fragment.discover.foryou

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.dondja.dondja.app.di.mavericks.AssistedViewModelFactory
import com.dondja.dondja.app.di.mavericks.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ForYouViewModel @AssistedInject constructor(
    @Assisted state: ForYouViewState
) : MavericksViewModel<ForYouViewState>(state) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ForYouViewModel, ForYouViewState> {
        override fun create(state: ForYouViewState): ForYouViewModel
    }

    companion object :
        MavericksViewModelFactory<ForYouViewModel, ForYouViewState> by hiltMavericksViewModelFactory()
}