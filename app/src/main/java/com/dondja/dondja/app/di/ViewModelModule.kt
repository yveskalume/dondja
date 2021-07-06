package com.dondja.dondja.app.di


import com.dondja.dondja.app.di.mavericks.AssistedViewModelFactory
import com.dondja.dondja.app.di.mavericks.MavericksViewModelComponent
import com.dondja.dondja.app.di.mavericks.ViewModelKey
import com.dondja.dondja.ui.fragment.feed.feedall.FeedAllViewModel
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
}