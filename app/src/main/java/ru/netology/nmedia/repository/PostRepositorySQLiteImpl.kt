package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.dao.PostDao

class PostRepositorySQLiteImpl (
    private val dao: PostDao, val context: Context) : PostRepository {

    private val gson = Gson()
    private val type = TypeToken.getParameterized(String::class.java).type
    private val fileName = "rough_copy.json"
    private var roughCopy = ""


    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)


    init {
        posts = dao.getAll()
        data.value = posts

        val file = context.filesDir.resolve(fileName)
        if (file.exists()) {
            context.openFileInput(fileName).bufferedReader().use {
                roughCopy = gson.fromJson(it, type)
            }
        } else {
            sync()
        }

    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        dao.likeById(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
            )
        }
        data.value = posts
    }

    override fun watchById(id: Long) {
        dao.watchById(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(views = it.views + 1)
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        dao.shareById(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(reposts = it.reposts + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        posts = if (id == 0L) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved
            }
        }
        roughCopy = ""
        sync()
        data.value = posts
    }

    private fun sync() {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(roughCopy))
        }
    }

    override fun saveRoughCopy(text: String) {
        roughCopy = text
        sync()
    }

    override fun getRoughCopy(): String {
        context.openFileInput(fileName).bufferedReader().use {
            roughCopy = gson.fromJson(it, type)
        }
        sync()
        return roughCopy
    }

}
