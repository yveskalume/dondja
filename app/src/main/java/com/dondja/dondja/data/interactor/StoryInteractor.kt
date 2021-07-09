package com.dondja.dondja.data.interactor

import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.entity.Story
import com.dondja.dondja.data.firebaseconstant.FirestoreReferences
import com.dondja.dondja.data.util.Crud
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.collectAsFlow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class StoryInteractor @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : Crud<Story> {

    private val ref = FirestoreReferences
    private val currentUser by lazy { auth.currentUser }

    override fun getAll(): Flow<Result<List<Story>>> {
        TODO("Not yet implemented")
    }

    override fun getOneById(uid: String): Flow<Result<Story>> {
        TODO("Not yet implemented")
    }

    override fun getAllFromFlowing(): Flow<Result<List<Story>>> {
        val query = firestore.collection(ref.posts)
//            .whereArrayContains(Post::followersUid.name, currentUser!!.uid)
        return query.collectAsFlow()
    }

    override suspend fun updateOne(data: Story) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOneByUid(uid: String) {
        TODO("Not yet implemented")
    }
}