package com.dondja.dondja.data.interactor

import com.dondja.dondja.data.entity.Theme
import com.dondja.dondja.data.extension.Crud
import com.dondja.dondja.data.extension.Result
import kotlinx.coroutines.flow.Flow

class ThemeInteractor : Crud<Theme> {
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