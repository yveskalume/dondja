package com.dondja.dondja.data.extension

import kotlinx.coroutines.flow.Flow

interface Crud<T> {
    fun getAll() : Flow<Result<List<T>>>
    fun getOneById(uid: String) : Flow<Result<T>>
    fun updateOneByUid(uid: String) : Flow<Result<Unit>>
    fun deleteOneByUid(uid: String) : Flow<Result<Unit>>
}