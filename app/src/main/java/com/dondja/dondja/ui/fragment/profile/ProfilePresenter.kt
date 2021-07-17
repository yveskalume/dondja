package com.dondja.dondja.ui.fragment.profile

import com.dondja.dondja.data.interactor.UserInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfilePresenter @Inject constructor(private val interactor: UserInteractor) {

    suspend fun getUserByUid(uid: String) = withContext(Dispatchers.IO) {
        interactor.getOneById(uid)
    }
}