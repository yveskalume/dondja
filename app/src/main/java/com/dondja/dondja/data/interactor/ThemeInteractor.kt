package com.dondja.dondja.data.interactor

import com.dondja.dondja.data.entity.Theme
import com.dondja.dondja.data.util.Crud
import com.dondja.dondja.data.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeInteractor @Inject constructor() : Crud<Theme> {
    override fun getAll(): Flow<Result<List<Theme>>> {
        TODO("Not yet implemented")
    }

    override fun getOneById(uid: String): Flow<Result<Theme>> {
        TODO("Not yet implemented")
    }

    override fun updateOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}