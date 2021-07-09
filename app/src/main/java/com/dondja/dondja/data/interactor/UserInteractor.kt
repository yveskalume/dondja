package com.dondja.dondja.data.interactor

import com.google.firebase.firestore.FirebaseFirestore
import com.dondja.dondja.data.entity.User
import com.dondja.dondja.data.firebaseconstant.FirestoreReferences
import com.dondja.dondja.data.util.Crud
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.collectAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserInteractor @Inject constructor(
    val  firestore: FirebaseFirestore
): Crud<User> {

    private val ref = FirestoreReferences
    override fun getAll(): Flow<Result<List<User>>> {
        val query = firestore.collection(ref.users)
        return query.collectAsFlow()
    }

    override fun getOneById(uid: String): Flow<Result<User>> {
        val query = firestore.document("${ref.users}/$uid")
        return query.collectAsFlow()
    }

}