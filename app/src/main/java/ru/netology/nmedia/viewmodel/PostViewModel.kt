package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.db.AppDb
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryImpl

val empty = Post(
     id = 0,
 author = "",
 content = "",
 published = "",
 likedByMe = false,
 likes = 0,
 avatar = R.drawable.posts_avatars_foreground,
 views = 0,
 reposts = 0,
    video = null
)
class PostViewModel(application: Application): AndroidViewModel(application) {

//    private val repository: PostRepository = PostRepositoryFileImpl(application)
    private val repository: PostRepository = PostRepositoryImpl(
        AppDb.getInstance(application).postDao, context = application
    )
    val data = repository.getAll()
    val edited = MutableLiveData(empty)


    fun roughCopy(): String{
        return repository.getRoughCopy()
    }

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

    fun repealEdit(){
        edited.value = empty
    }

    fun likeById(id: Long) = repository.likeById(id)
    fun watchById(id: Long) = repository.watchById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun saveRoughCopy(text: String) = repository.saveRoughCopy(text)
}