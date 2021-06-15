package com.yvkalume.core_data.interactor

import com.yvkalume.core_data.entity.Theme
import com.yvkalume.core_data.util.Crud
import com.yvkalume.core_data.util.Result
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