package com.dondja.dondja.ui.fragment.profile

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

class ProfileViewModel @AssistedInject constructor(
    @Assisted state: ProfileViewState,
    private val presenter: ProfilePresenter
) : MavericksViewModel<ProfileViewState>(state) {

    fun getData(uid: String) = viewModelScope.launch {
        presenter.getUserByUid(uid).map { it.data!! }.execute {
            copy(user = it)
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ProfileViewModel, ProfileViewState> {
        override fun create(state: ProfileViewState): ProfileViewModel
    }

    companion object :
        MavericksViewModelFactory<ProfileViewModel, ProfileViewState> by hiltMavericksViewModelFactory()
}