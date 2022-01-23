package ru.netology.nmedia.repository

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.Post
import ru.netology.nmedia.R

class PostRepositoryFileImpl(val context: Context) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    val filename = "posts.json"

    private var nextId = 1L
    private var  posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                nextId = posts.maxOfOrNull { post -> post.id }?.inc() ?: 1L
                data.value = posts
            }
        } else {
            sync()
        }
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe, likes = if (it.likedByMe) it.likes - 1 else it.likes + 1)
        }
        data.value = posts
        sync()
    }

    override fun watchById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else it.copy(views = it.views + 1)
        }
        data.value = posts
        sync()
    }

    override fun shareById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else it.copy(reposts = it.reposts + 1)
        }
        data.value = posts
        sync()
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
        sync()
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(post.copy(id = nextId++, author = "Me", published = "Now")) + posts
        } else {
            posts = posts.map { if (it.id != post.id) it else it.copy(content = post.content) }
        }
        data.value = posts
        sync()
    }

    override fun saveRoughCopy(text: String): String {
        TODO("Not yet implemented")
    }

    override fun getRoughCopy(): String {
        TODO("Not yet implemented")
    }


    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }
}