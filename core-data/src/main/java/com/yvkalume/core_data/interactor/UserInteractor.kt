package com.yvkalume.core_data.interactor

import com.google.firebase.firestore.FirebaseFirestore
import com.yvkalume.core_data.entity.User
import com.yvkalume.core_data.firebaseconstant.FirestoreReferences
import com.yvkalume.core_data.util.Crud
import com.yvkalume.core_data.util.Result
import com.yvkalume.core_data.util.collectAsFlow
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

    override fun updateOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteOneByUid(uid: String): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

}