package com.dondja.dondja.data.entity

import java.util.*

data class User(
    val uid: String = "",
    val profileUrl: String = "",
    val firstName: String = "",
    val secondName: String = "",
    val displayName: String = "",
    val email: String= "",
    val sexe: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val followersNumber: Int = 0,
    val followingNumber: Int = 0,
    val followersUid: List<String> = listOf(),
    val followingThemesUid: List<String> = listOf(),
    val isEmailValidate: Boolean = false,
    val isPhoneValidate: Boolean = false,
    val isValid: Boolean = true,
    val isVerified: Boolean = false,
    val birthday: Date? = null
)