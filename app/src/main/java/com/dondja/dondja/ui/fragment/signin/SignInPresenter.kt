package com.dondja.dondja.ui.fragment.signin

import androidx.lifecycle.MutableLiveData
import com.dondja.dondja.data.interactor.AccountInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInPresenter @Inject constructor(private val accountInteractor: AccountInteractor) {

    suspend fun signInWithPhoneNumber() = withContext(Dispatchers.IO) {
//        accountInteractor.signUpWithPhoneNumber()
    }

    suspend fun signInWithEmail(email: String,password: String) = withContext(Dispatchers.IO) {
        accountInteractor.signInWithEmailAndPassword(email,password)
    }

}