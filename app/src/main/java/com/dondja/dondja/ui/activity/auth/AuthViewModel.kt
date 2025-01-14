package com.dondja.dondja.ui.activity.auth

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondja.dondja.data.entity.User
import com.dondja.dondja.data.interactor.AccountInteractor
import com.dondja.dondja.data.interactor.ThemeInteractor
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.util.Log
import com.google.firebase.auth.PhoneAuthCredential
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
    val signUpFlowState = MutableLiveData<Result<Unit>>()
    val signUpWithPhoneFlowState = MutableLiveData<Result<Unit>>()

    fun log() {
        Log("user $user")
    }


    private suspend fun saveUserInfo(user: User) {
      interactor.saveUserInfo(user)
    }

    fun signUpWithEmailAndPassword() = viewModelScope.launch {
        Log("$user")
        try {
            signUpFlowState.postValue(Result.Loading)
            val userUid = interactor.signUpWithEmailAndPassword(user.email,user.password)
            val user = user.copy(uid = userUid)
            saveUserInfo(user)
            signUpFlowState.postValue(Result.Success(Unit))
        } catch (e: Exception) {
            Log(e.toString())
            signUpFlowState.postValue(Result.Error(e))
        }
    }

    fun signUpWithPhoneNumber() = viewModelScope.launch {
        try {
            signUpWithPhoneFlowState.postValue(Result.Loading)
            val userUid = interactor.signUpWithPhoneNumber(user.phoneNumber,user.password)
            val user = user.copy(uid = userUid)
            saveUserInfo(user)
            signUpWithPhoneFlowState.postValue(Result.Success(Unit))
        } catch (e: Exception) {
            signUpWithPhoneFlowState.postValue(Result.Error(e))
        }
    }

    fun uploadUserProfile(uri: Uri) = viewModelScope.launch {
        try {
            imageUploadingState.postValue(Result.Loading)
            val profileUrl = interactor.uploadProfilePicture(uri)
            interactor.updateUserProfilePicture(profileUrl)
            imageUploadingState.postValue(Result.Success(user.profileUrl))
        } catch (e : Exception) {
            Log(e.message.toString())
            imageUploadingState.postValue(Result.Error(e))
        }
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
}