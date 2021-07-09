package com.dondja.dondja.data.util

import kotlinx.coroutines.flow.Flow

interface Crud<T> {
    fun getAll() : Flow<Result<List<T>>> { TODO() }
    fun getOneById(uid: String) : Flow<Result<T>> { TODO() }
    suspend fun updateOne(data: T) { TODO() }
    suspend fun deleteOneByUid(uid: String) { TODO() }
    fun getAllFromFlowing() : Flow<Result<List<T>>> { TODO() }
}