package com.dondja.dondja.app.di


import com.dondja.dondja.app.di.mavericks.AssistedViewModelFactory
import com.dondja.dondja.app.di.mavericks.MavericksViewModelComponent
import com.dondja.dondja.app.di.mavericks.ViewModelKey
import com.dondja.dondja.ui.fragment.discover.popular.PopularViewModel
import com.dondja.dondja.ui.fragment.feed.feedall.FeedAllViewModel
import com.dondja.dondja.ui.fragment.postview.PostViewViewModel
import com.dondja.dondja.ui.fragment.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedAllViewModel::class)
    fun feedAllViewModelFactory(factory: FeedAllViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(PostViewViewModel::class)
    fun postViewViewModelFactory(factory: PostViewViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    fun popularViewModelFactory(factory: PopularViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun profileViewModelFactory(factory: ProfileViewModel.Factory): AssistedViewModelFactory<*, *>
}