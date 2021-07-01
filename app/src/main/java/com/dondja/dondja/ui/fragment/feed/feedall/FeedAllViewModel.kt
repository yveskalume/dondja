package com.dondja.dondja.ui.fragment.feed.feedall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.entity.Story
import com.dondja.dondja.data.interactor.PostInteractor
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.data
import com.dondja.dondja.data.util.succeeded
import com.dondja.dondja.util.Log
import com.dondja.dondja.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedAllViewModel @Inject constructor(
    private val presenter: FeedAllPresenter
) : ViewModel() {

//    private val _posts = MutableLiveData<Result<List<Post>>>()
//    val post: LiveData<Result<List<Post>>>
//        get() = _posts

    private var feedData = FeedAllData(emptyList(), emptyList())
    private val _data = MutableLiveData<Result<FeedAllData>>()
    val data: LiveData<Result<FeedAllData>>
        get() = _data

//    private val _stories = MutableLiveData<Result<List<Story>>>()
//    val stories: LiveData<Result<List<Story>>>
//        get() = _stories

    init {
        processAllData()
    }

    private fun processAllData() = viewModelScope.launch {
        _data.value = Result.Loading
        try {
            getAllPosts()
            getAllStories()
        } catch (e: Exception) {
            _data.value = Result.Error(Exception("Une erreur s'est produite"))
        }
    }

    private suspend fun getAllPosts() {
        presenter.getAllPostFromFollowing().collect {
            feedData = feedData.copy(posts = it.data!!)
            _data.postValue(Result.Success(feedData))
        }
    }

    private suspend fun getAllStories() {
        presenter.getAllStoryFromFollowing().collect {
            feedData = feedData.copy(stories = it.data!!)
            _data.postValue(Result.Success(feedData))
        }
    }
}
