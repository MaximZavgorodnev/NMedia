package ru.netology.nmedia

import android.net.Uri

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likes: Int,
    val avatar: Int,
    val views: Int,
    val reposts: Int,
    val video: Uri?
) {

}