package com.dondja.dondja.ui.fragment.createpost

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.entity.Theme
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class CreatePostViewModel @Inject constructor(private val presenter: CreatePostPresenter) : ViewModel() {
    private val urlsArray = ArrayList<String>()
    private val uris = ArrayList<Uri>()
    var post = Post()
    val imageUrls = MutableLiveData<ArrayList<String>>()

    private val _publishingState = MutableLiveData<Result<Unit>>()
    val publishingState : LiveData<Result<Unit>>
        get() = _publishingState

    private val _themes = MutableLiveData<Result<List<Theme>>>()
    val themes : LiveData<Result<List<Theme>>>
        get() = _themes

    init {
        getThemes()
    }

    private fun getThemes() = viewModelScope.launch {
        _themes.postValue(Result.Loading)
        try {
            presenter.getAllThemes().collect {
                _themes.postValue(it)
            }
        } catch (e: Exception) {
            _themes.postValue(Result.Error(e))
        }
    }


    fun bindImageUri(uri: Uri?) {
        if (uri == null)
            return
        uris.add(uri)
        urlsArray.add(uri.toString())
        imageUrls.postValue(urlsArray)
        Log(imageUrls.value.toString())
    }

    fun publishPost() = viewModelScope.launch {
        _publishingState.postValue(Result.Loading)
        try {
            val imagesUrls = presenter.uploadImagesList(uris)
            val newPost = post.copy(imagesUrls = imagesUrls)
            val postingResult = presenter.savePost(newPost)
            _publishingState.postValue(Result.Success(postingResult))
        } catch (e: Exception) {
            _publishingState.postValue(Result.Error(e))
        }
    }

    fun deleteImageUrl(url: String) {
        imageUrls.value?.remove(url)
    }
}