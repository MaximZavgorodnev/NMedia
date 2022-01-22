package ru.netology.nmedia.dao

import ru.netology.nmedia.Post

interface PostDao {
    fun getAll(): List<Post>
    fun likeById(id: Long)
    fun watchById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    fun save(post: Post): Post
}