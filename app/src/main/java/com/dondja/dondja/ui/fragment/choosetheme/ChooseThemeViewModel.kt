package com.dondja.dondja.ui.fragment.choosetheme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondja.dondja.data.entity.Theme
import com.dondja.dondja.data.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseThemeViewModel @Inject constructor(private val presenter: ChooseThemePresenter) : ViewModel() {

    private val _themes = MutableLiveData<Result<List<Theme>>>()
    val themes : LiveData<Result<List<Theme>>>
        get() = _themes

    private val _submittingThemesState = MutableLiveData<Result<Unit>>()
    val submittingThemesState : LiveData<Result<Unit>>
        get() = _submittingThemesState

    init {
        getAllThemes()
    }

    private fun getAllThemes() = viewModelScope.launch {
        presenter.getAllThemes().collect {
            _themes.postValue(it)
        }
    }

    fun setFollowingThemes(themes: List<String>) = viewModelScope.launch {
        _submittingThemesState.postValue(Result.Loading)
        try {
            presenter.setFollowingThemes(themes)
            _submittingThemesState.postValue(Result.Success(Unit))
        } catch (e: Exception) {
            _submittingThemesState.postValue(Result.Error(e))
        }
    }
}