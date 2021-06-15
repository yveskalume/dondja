package com.yvkalume.core_data.interactor

import com.yvkalume.core_data.entity.Post
import com.yvkalume.core_data.util.Crud
import com.yvkalume.core_data.util.Result
import kotlinx.coroutines.flow.Flow

class PostInteractor : Crud<Post> {
    override fun getAll(): Flow<Result<List<Post>>> {
        TODO("Not yet implemented")
    }

    override fun getOneById(uid: String): Flow<Result<Post>> {
        TODO("Not yet implemented")
    }

    override fun updateOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}