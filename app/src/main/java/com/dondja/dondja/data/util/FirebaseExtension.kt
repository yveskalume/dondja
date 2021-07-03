package com.dondja.dondja.data.util

import com.dondja.dondja.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
inline fun <reified T>DocumentReference.collectAsFlow() : Flow<Result<T>> {
    return callbackFlow {
        offer(Result.Loading)
        addSnapshotListener { value, error ->
            if (error != null || value == null) {
                if (!isClosedForSend) {
                    offer(Result.Error(Exception(error)))
                }
                close(error)
                return@addSnapshotListener
            }

            if (!isClosedForSend) {
                value.toObject<T>()?.let {
                    offer(Result.Success(it))
                }
            }
        }
        awaitClose()
    }
}

@ExperimentalCoroutinesApi
inline fun <reified T>Query.collectAsFlow(crossinline action: ((List<T>) -> List<T>) = { data -> data} ) : Flow<Result<List<T>>> {
    return callbackFlow {
        offer(Result.Loading)
        addSnapshotListener { value, error ->
            if (error != null || value == null) {
                if (!isClosedForSend) {
                    offer(Result.Error(Exception(error)))
                }
                close(error)
                return@addSnapshotListener
            }

            if (!isClosedForSend) {
                offer(Result.Success(action(value.toObjects(T::class.java))).also {
                    Log("data ${it.data}")
                })
            }
        }
        awaitClose()
    }
}

//fun CollectionReference.collectAsFlow() {
//
//}