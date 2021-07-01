package com.dondja.dondja.data.entity

import java.util.*

data class Post (
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val imagesUrls: List<String> = emptyList(),
    val videoUrl: String = "",
    val audioUrl: String = "",
    val userUid: String = "",
    val userDisplayName: String = "",
    val userProfilePicture: String = "",
    val commentsNumber: Long = 0,
    val likes: Long = 0,
    val shares: Long = 0,
    val themes: List<String> = emptyList(),
    val followersUid: List<String> = listOf(),
    val likersUid: List<String> = listOf(),
    val commentatorsUid: List<String> = listOf(),
    val isVideo: Boolean = false,
    val isShared: Boolean = false,
    val updatedAt: Date? = null,
    val createdAt: Date? = null,
)