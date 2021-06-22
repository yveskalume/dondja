package com.dondja.dondja.ui.fragment.feed.feedall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondja.dondja.data.interactor.PostInteractor
import com.dondja.dondja.data.util.data
import com.dondja.dondja.data.util.succeeded
import com.dondja.dondja.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedAllViewModel @Inject constructor(
    private val presenter: FeedAllPresenter
) : ViewModel() {

    private val _data = MutableLiveData<ViewState<FeedAllData>>()
    val data: LiveData<ViewState<FeedAllData>>
        get() = _data

    init {
        getAllPost()
    }

    private fun getAllPost() = viewModelScope.launch {
        try {
            val postFlow = presenter.getAllPostFromFollowing()
            val storyFlow = presenter.getAllStoryFromFollowing()
            combine(postFlow,storyFlow) { posts, stories ->
                _data.postValue(ViewState.Success(FeedAllData(posts,stories)))
            }
        } catch (e: Exception) {
            _data.postValue(ViewState.Failure(Exception("Une erreur s'est produite")))
        }
    }
}
