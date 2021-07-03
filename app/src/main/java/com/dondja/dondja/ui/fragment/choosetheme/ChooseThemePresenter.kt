package com.dondja.dondja.ui.fragment.choosetheme

import com.dondja.dondja.data.interactor.AccountInteractor
import com.dondja.dondja.data.interactor.ThemeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChooseThemePresenter @Inject constructor(
    private val themeInteractor: ThemeInteractor,
    private val accountInteractor: AccountInteractor
) {

    suspend fun getAllThemes() = withContext(Dispatchers.IO) {
        themeInteractor.getAll()
    }

    suspend fun setFollowingThemes(themes: List<String>) = withContext(Dispatchers.IO) {
        accountInteractor.setFollowingThemes(themes)
    }

}