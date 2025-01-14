package com.dondja.dondja.data.interactor

import android.net.Uri
import com.dondja.dondja.data.entity.Post
import com.dondja.dondja.data.entity.Story
import com.dondja.dondja.data.firebaseconstant.FirebaseStorageReferences
import com.dondja.dondja.data.firebaseconstant.FirestoreReferences
import com.dondja.dondja.data.util.Crud
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.collectAsFlow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class PostInteractor @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val auth: FirebaseAuth
    ) : Crud<Post> {

    private val firestoreRef = FirestoreReferences
    private val storageRef = FirebaseStorageReferences
    private val currentUser by lazy { auth.currentUser }

    override fun getAll(): Flow<Result<List<Post>>> {
        return firestore.collection(firestoreRef.posts).collectAsFlow()
    }

    override fun getOneById(uid: String): Flow<Result<Post>> {
        return firestore.document("${firestoreRef.posts}/$uid").collectAsFlow()
    }

    override suspend fun updateOne(data: Post) {
        firestore.document("${firestoreRef.posts}/${data.uid}").set(data).await()
    }

    override suspend fun deleteOneByUid(uid: String) {
        firestore.document("${firestoreRef.posts}/$uid").delete().await()
    }

    @ExperimentalCoroutinesApi
    override fun getAllFromFlowing() : Flow<Result<List<Post>>> {
        val query = firestore.collection(firestoreRef.posts)
//            .whereArrayContains(Story::followersUid.name, currentUser!!.uid)
        return query.collectAsFlow()
    }

    suspend fun savePost(newPost: Post) {
        val collection = firestore.collection(firestoreRef.posts)
        val docUid = collection.document().id
        val post = newPost.copy(uid = docUid)
        collection.document(docUid).set(post).await()
    }

    suspend fun uploadImages(uris: List<Uri>): ArrayList<String> {
        val urls = ArrayList<String>()
        for (uri in uris) {
            val query = firebaseStorage.getReference(storageRef.posts).child("${currentUser!!.uid}_${System.currentTimeMillis().toString()}")
            query.putFile(uri).await()
            val imgUrl = query.downloadUrl.await().toString()
            urls.add(imgUrl)
        }

        return urls
    }

    suspend fun likeOrDislike(post: Post) {
        val likersUid = post.followersUid
        if (post.followersUid.contains(currentUser!!.uid)) {
            likersUid.remove(currentUser!!.uid)
        } else {
            likersUid.add(currentUser!!.uid)
        }
        firestore.document("${firestoreRef.posts}/${post.uid}")
            .update(Post::followersUid.name,likersUid).await()
    }

    suspend fun getPopularPosts() : Flow<Result<List<Post>>> {
        val query = firestore.collection(firestoreRef.posts)
            .orderBy(Post::likes.name,Query.Direction.DESCENDING)
        return query.collectAsFlow()
    }
}