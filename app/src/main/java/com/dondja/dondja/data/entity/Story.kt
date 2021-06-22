package com.dondja.dondja.data.entity

import java.util.*

data class Story(
    val uid: String = "",
    val content: String = "",
    val contentType: String ="",
    val views: Int = 0,
    val viewersUid: List<String> = emptyList(),
    val followersUid: List<String> = emptyList(),
    val createdAt: Date? = null
) {
    fun isCreatedToday(): Boolean {
        return if (this.createdAt != null) {
            this.createdAt.after(Date(System.currentTimeMillis()))
        } else {
            false
        }
    }
}