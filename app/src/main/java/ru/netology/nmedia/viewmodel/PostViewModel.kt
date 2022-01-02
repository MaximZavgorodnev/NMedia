package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data: LiveData<Post> = repository.get()
    fun like() = repository.like()
    fun view() = repository.view()
    fun repost() = repository.repost()
}