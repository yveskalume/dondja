package com.dondja.dondja.data.interactor

import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.entity.Story
import com.dondja.dondja.data.firebaseconstant.FirestoreReferences
import com.dondja.dondja.data.util.Crud
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.collectAsFlow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostInteractor @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
    ) : Crud<Post> {

    private val ref = FirestoreReferences
    private val currentUser by lazy { auth.currentUser }

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

    @ExperimentalCoroutinesApi
    override fun getAllFromFlowing(): Flow<Result<List<Post>>> {
        val query = firestore.collection(ref.posts)
            .whereArrayContains(Story::followersUid.name, currentUser!!.uid)
        return query.collectAsFlow { post -> post.sortedBy { it.createdAt } }
    }
}