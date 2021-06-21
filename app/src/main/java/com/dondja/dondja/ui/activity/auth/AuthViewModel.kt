package com.dondja.dondja.ui.activity.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondja.dondja.data.entity.User
import com.dondja.dondja.data.interactor.AccountInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val interactor: AccountInteractor) : ViewModel() {

    val user = User()

    fun saveUserInfo(user: User) = viewModelScope.launch {
        interactor.saveUserInfo(user)
    }

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        interactor.signUpWithEmailAndPassword(email,password)
    }
}