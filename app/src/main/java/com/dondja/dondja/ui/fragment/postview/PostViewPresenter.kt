package com.dondja.dondja.ui.fragment.postview

import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.interactor.PostInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostViewPresenter @Inject constructor(
    private val postInteractor: PostInteractor) {

    suspend fun likeOrDislikePost(post: Post) = withContext(Dispatchers.IO) {
        postInteractor.likeOrDislike(post)
    }
}