package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

val empty = Post(
     id = 0,
 author = "",
 content = "",
 published = "",
 likedByMe = false,
 likes = 0,
 avatar = R.drawable.ic_baseline_check_24,
 views = 0,
 reposts = 0
)
class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data: LiveData<List<Post>> = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post){
        edited.value = post
    }

    fun changeContent(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content == text) {
                return
            }
            edited.value = edited.value?.copy(content = text)
        }
    }
    fun likeById(id: Long) = repository.likeById(id)
    fun watchById(id: Long) = repository.watchById(id)
    fun repostById(id: Long) = repository.repostById(id)
    fun removeById(id: Long) = repository.removeById(id)
}