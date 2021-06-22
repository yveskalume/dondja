package com.dondja.dondja.ui.activity.auth

import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondja.dondja.data.entity.User
import com.dondja.dondja.data.interactor.AccountInteractor
import com.dondja.dondja.data.interactor.ThemeInteractor
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val interactor: AccountInteractor,
    private val themeInteractor: ThemeInteractor
    ) : ViewModel() {

    var user = User()

    val imageUploadingState = MutableLiveData<Result<String>>()

    fun log() {
        Log("user $user")
    }

//    fun uploadProfilePic(uri: Uri) = viewModelScope.launch {
//        imageUploadingState.postValue(Result.Loading)
//        user = user.copy(profileUrl = interactor.uploadProfilePicture(uri))
//        imageUploadingState.postValue(Result.Success(user.profileUrl))
//        try {

//        } catch (e : Exception) {
//            Log(e.message.toString())
//            imageUploadingState.postValue(Result.Error(e))
//        }
//    }

    private suspend fun saveUserInfo(user: User) {
      interactor.saveUserInfo(user)
    }

    fun signUpWithEmailAndPassword() = viewModelScope.launch {
        Log("$user")
        val userUid = interactor.signUpWithEmailAndPassword(user.email,user.password)
        val userProfile = interactor.uploadProfilePicture(user.profileUrl.toUri())
        val user = user.copy(uid = userUid,profileUrl = userProfile)
        saveUserInfo(user)
    }
}