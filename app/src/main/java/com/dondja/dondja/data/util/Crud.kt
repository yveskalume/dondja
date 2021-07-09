package com.dondja.dondja.data.util

import kotlinx.coroutines.flow.Flow

interface Crud<T> {
    fun getAll() : Flow<Result<List<T>>>
    fun getOneById(uid: String) : Flow<Result<T>>
    suspend fun updateOne(data: T)
    suspend fun deleteOneByUid(uid: String)
    fun getAllFromFlowing() : Flow<Result<List<T>>>
}