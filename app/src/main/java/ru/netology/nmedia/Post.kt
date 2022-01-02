package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likedByMe: Boolean = false,
    val avatar: Int,
    var viewByMe: Boolean = false,
    var reposts: Boolean = false
) {

}