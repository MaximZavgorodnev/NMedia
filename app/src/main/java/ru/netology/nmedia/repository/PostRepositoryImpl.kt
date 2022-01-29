package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.Post
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.entity.PostEntity

class PostRepositoryImpl (
    private val dao: PostDao, val context: Context) : PostRepository {

    private val gson = Gson()
    private val type = TypeToken.getParameterized(String::class.java).type
    private val fileName = "rough_copy.json"
    private var roughCopy = ""

    init {

        val file = context.filesDir.resolve(fileName)
        if (file.exists()) {
            context.openFileInput(fileName).bufferedReader().use {
                roughCopy = gson.fromJson(it, type)
            }
        } else {
            sync()
        }

    }

    override fun getAll(): LiveData<List<Post>> = Transformations.map(dao.getAll()) { list ->
            list.map{ Post(it.id, it.author, it.content, it.published, it.likedByMe, it.likes, it.avatar, it.views, it.reposts, it.video) }
    }


    override fun likeById(id: Long) = dao.likeById(id)

    override fun watchById(id: Long) = dao.watchById(id)

    override fun shareById(id: Long) = dao.shareById(id)


    override fun removeById(id: Long) = dao.removeById(id)


    override fun save(post: Post) {
        dao.save(PostEntity.fromDto(post))
        roughCopy = ""
        sync()
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
