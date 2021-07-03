package com.dondja.dondja.data.entity

data class Theme(
    val uid: String = "",
    val title: String = "",
    val followersUid: List<String> = listOf(),
)