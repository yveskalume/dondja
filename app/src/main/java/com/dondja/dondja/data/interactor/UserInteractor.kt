package com.dondja.dondja.data.interactor

import com.dondja.dondja.data.entity.User
import com.dondja.dondja.data.extension.Crud
import com.dondja.dondja.data.extension.Result
import kotlinx.coroutines.flow.Flow

class UserInteractor : Crud<User> {
    override fun getAll(): Flow<Result<List<User>>> {
        TODO("Not yet implemented")
    }

    override fun getOneById(uid: String): Flow<Result<User>> {
        TODO("Not yet implemented")
    }

    override fun updateOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

}