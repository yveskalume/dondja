package com.dondja.dondja.ui.fragment.discover.popular

import com.dondja.dondja.data.interactor.PostInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularPresenter @Inject constructor(private val postInteractor: PostInteractor) {

    suspend fun getPopularPosts() = withContext(Dispatchers.IO) {
        postInteractor.getPopularPosts()
    }
}