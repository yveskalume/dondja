package com.dondja.dondja.ui.fragment.createpost

import android.net.Uri
import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.interactor.PostInteractor
import com.dondja.dondja.data.interactor.ThemeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreatePostPresenter @Inject constructor(
    private val postInteractor: PostInteractor,
    private val themeInteractor: ThemeInteractor
){

    suspend fun uploadImagesList(uris: List<Uri>) = withContext(Dispatchers.IO) {
        postInteractor.uploadImages(uris)
    }

    suspend fun savePost(post: Post) = withContext(Dispatchers.IO) {
        postInteractor.savePost(post)
    }

    @ExperimentalCoroutinesApi
    suspend fun getAllThemes() = withContext(Dispatchers.IO) {
        themeInteractor.getAll()
    }
}