package com.dondja.dondja.data.interactor

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.dondja.dondja.data.entity.User
import com.dondja.dondja.data.firebaseconstant.FirebaseStorageReferences
import com.dondja.dondja.data.firebaseconstant.FirestoreReferences
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.actionCodeSettings
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AccountInteractor @Inject constructor (
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage,
    private val firestore: FirebaseFirestore
    )
{
    private val ref = FirestoreReferences
    private val currentUser by lazy {
        auth.currentUser
    }

    suspend fun signUpWithEmailAndPassword(email: String, password: String): String {
        auth.createUserWithEmailAndPassword(email,password).await()
        sendConfirmationEmail(email)
        return auth.currentUser!!.uid
    }

    suspend fun signInWithEmailAndPassword(email: String,password: String): FirebaseUser? {
        return auth.signInWithEmailAndPassword(email,password).await().user
    }

    suspend fun signInWithPhoneNumber(phoneNumber: String,password: String) : FirebaseUser? {
        val concatenatedPhoneNumber = "$phoneNumber@dondja.com".trim()
        return auth.signInWithEmailAndPassword(concatenatedPhoneNumber,password).await().user
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
            .setDisplayName("${user.firstName} ${user.secondName}")
            .build()
        auth.currentUser?.updateProfile(builder)
    }

    suspend fun updateUserProfilePicture(url: String) {
        firestore.document("${ref.users}/${auth.currentUser!!.uid}")
            .update(User::profileUrl.name,url).await()

        val builder = UserProfileChangeRequest.Builder()
            .setPhotoUri(Uri.parse(url))
            .build()
        auth.currentUser?.updateProfile(builder)
    }

    suspend fun sendRestPasswordEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    private fun sendConfirmationEmail(email: String) {
        val actionCodeSettings = actionCodeSettings {
            url = "https://dondja.com"
            setAndroidPackageName(
                "com.donja.donja",
                true,
                "1"
            )
            handleCodeInApp = true
            dynamicLinkDomain = "wijea"
        }
        auth.sendSignInLinkToEmail(email,actionCodeSettings)
    }

    suspend fun signUpWithPhoneNumber(phoneNumber: String,password: String): String {
        val concatenatedPhoneNumber = "$phoneNumber@dondja.com".trim()
        auth.createUserWithEmailAndPassword(concatenatedPhoneNumber, password).await()
        return auth.currentUser!!.uid
    }

    suspend fun updatePassword(password: String) {
        auth.currentUser!!.updatePassword(password).await()
    }

    suspend fun setFollowingThemes(themes: List<String>) {
        firestore.document("${ref.users}/${currentUser!!.uid}")
            .update(User::followingThemes.name,themes).await()
    }
}