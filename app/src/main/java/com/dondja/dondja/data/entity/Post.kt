package com.dondja.dondja.data.entity

import java.util.*

data class Post (
    val uid: String,
    val description: String,
    val imagesUrls: List<String>,
    val videoUrl: String,
    val userUid: String,
    val commentsNumber: Int = 0,
    val likes: Int = 0,
    val shares: Int = 0,
    val followersUid: List<String> = listOf(),
    val likersUid: List<String> = listOf(),
    val commentatorsUid: List<String> = listOf(),
    val isVideo: Boolean = false,
    val isShared: Boolean = false,
    val updatedAt: Date? = null,
    val createdAt: Date? = null,
)