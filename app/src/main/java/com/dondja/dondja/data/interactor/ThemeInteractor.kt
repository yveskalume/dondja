package com.dondja.dondja.data.interactor

import com.dondja.dondja.data.entity.Theme
import com.dondja.dondja.data.firebaseconstant.FirestoreReferences
import com.dondja.dondja.data.util.Crud
import com.dondja.dondja.data.util.Result
import com.dondja.dondja.data.util.collectAsFlow
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeInteractor @Inject constructor(private val firestore: FirebaseFirestore) : Crud<Theme> {

    val ref = FirestoreReferences

    @ExperimentalCoroutinesApi
    override fun getAll(): Flow<Result<List<Theme>>> {
        return firestore.collection(ref.themes)
            .collectAsFlow()
    }

    override fun getOneById(uid: String): Flow<Result<Theme>> {
        return firestore.document("${ref.themes}/$uid").collectAsFlow()
    }

    fun getAllFollowedWithUserUid(userUid: String): Flow<Result<List<Theme>>> {
        val query = firestore.collection(ref.themes)
            .whereArrayContains(Theme::followersUid.name, userUid)
        return query.collectAsFlow()
    }

//    suspend fun addFollowingUser(themeUid: String,userUid: String) {
//        firestore.document("${ref.themes}/$themeUid")
//            .update(Theme::followersUid.name,userUid)
//    }
}