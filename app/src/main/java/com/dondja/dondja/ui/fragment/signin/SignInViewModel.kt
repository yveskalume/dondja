package com.dondja.dondja.ui.fragment.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.util.Log
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(val presenter: SignInPresenter) : ViewModel() {

    private val _loginState = MutableLiveData<Result<FirebaseUser>>()
    val loginState: LiveData<Result<FirebaseUser>>
        get() = _loginState

    fun signInWithEmail(email: String,password: String) = viewModelScope.launch {
        try {
            _loginState.postValue(Result.Loading)
            val user = presenter.signInWithEmail(email,password)
            _loginState.postValue(Result.Success(user!!))
        } catch (e : Exception) {
            _loginState.postValue(Result.Error(e))
            Log(e.toString())
        }
    }

    fun signInWithPhoneNumber(phone: String,password: String) = viewModelScope.launch {
        try {
            _loginState.postValue(Result.Loading)
            val user = presenter.signInWithPhoneNumber(phone,password)
            _loginState.postValue(Result.Success(user!!))
        } catch (e : Exception) {
            _loginState.postValue(Result.Error(e))
            Log(e.toString())
        }
    }

}