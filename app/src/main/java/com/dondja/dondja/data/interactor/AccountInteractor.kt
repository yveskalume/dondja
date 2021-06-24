package com.dondja.dondja.data.interactor

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.dondja.dondja.data.entity.User
import com.dondja.dondja.data.firebaseconstant.FirebaseStorageReferences
import com.dondja.dondja.data.firebaseconstant.FirestoreReferences
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountInteractor @Inject constructor (
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore
    )
{
    private val ref = FirestoreReferences

    suspend fun signUpWithEmailAndPassword(email: String, password: String): String {
        auth.createUserWithEmailAndPassword(email,password).await()
        return auth.currentUser!!.uid
    }

    suspend fun signInWithEmailAndPassword(email: String,password: String): FirebaseUser? {
        return auth.signInWithEmailAndPassword(email,password).await().user
    }

    suspend fun uploadProfilePicture(profileUri: Uri): String {
        val uploadTask = storage.getReference(FirebaseStorageReferences.profile)
            .child(auth.currentUser!!.uid)
        uploadTask.putFile(profileUri).await()
        return uploadTask.downloadUrl.await().toString()
    }

    suspend fun saveUserInfo(user: User) {
        firestore.collection(ref.users)
            .document(user.uid)
            .set(user)
            .await()
        if (auth.currentUser?.photoUrl.toString() == user.profileUrl) return
        val builder = UserProfileChangeRequest.Builder()
            .setDisplayName(user.displayName)
            .setPhotoUri(Uri.parse(user.profileUrl))
            .build()
        auth.currentUser?.updateProfile(builder)
    }

    suspend fun sendRestPasswordEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    suspend fun sendConfirmationEmail(email: String) {

    }

    suspend fun updatePassword(password: String) {
        auth.currentUser!!.updatePassword(password).await()
    }
}