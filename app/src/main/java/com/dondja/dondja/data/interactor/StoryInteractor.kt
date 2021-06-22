package com.dondja.dondja.data.interactor

import com.dondja.dondja.data.entity.Story
import com.dondja.dondja.data.util.Crud
import com.dondja.dondja.data.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoryInteractor @Inject constructor() : Crud<Story> {
    override fun getAll(): Flow<Result<List<Story>>> {
        TODO("Not yet implemented")
    }

    override fun getOneById(uid: String): Flow<Result<Story>> {
        TODO("Not yet implemented")
    }

    override fun updateOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getAllFromFlowing(): Flow<Result<List<Story>>> {
        TODO("Not yet implemented")
    }
}