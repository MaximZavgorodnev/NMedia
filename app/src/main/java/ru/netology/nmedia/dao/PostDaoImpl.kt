package ru.netology.nmedia.dao

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import ru.netology.nmedia.Post

class PostDaoImpl(private val db: SQLiteDatabase) : PostDao {
    companion object{
        val DDL = """
        CREATE TABLE ${PostColumns.TABLE} (
            ${PostColumns.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${PostColumns.COLUMN_AUTHOR} TEXT NOT NULL
            ${PostColumns.COLUMN_CONTENT} TEXT NOT NULL
            ${PostColumns.COLUMN_PUBLISHED} TEXT NOT NULL
            ${PostColumns.COLUMN_LIKED_BY_ME} BOOLEAN NOT NULL DEFAULT 0
            ${PostColumns.COLUMN_LIKES} INTEGER NOT NULL DEFAULT 0
            ${PostColumns.COLUMN_AVATAR} INTEGER NOT NULL DEFAULT 0
            ${PostColumns.COLUMN_VIEWS} INTEGER NOT NULL DEFAULT 0
            ${PostColumns.COLUMN_REPOSTS} INTEGER NOT NULL DEFAULT 0
            ${PostColumns.COLUMN_VIDEO} TEXT DEFAULT NULL
        );
        """.trimIndent()
    }

    object PostColumns {
        const val TABLE = "posts"
        const val COLUMN_ID = "id"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_PUBLISHED = "published"
        const val COLUMN_LIKED_BY_ME = "likedById"
        const val COLUMN_LIKES = "likes"
        const val COLUMN_AVATAR = "avatar"
        const val COLUMN_VIEWS = "views"
        const val COLUMN_REPOSTS = "reposts"
        const val COLUMN_VIDEO = "video"
        val ALL_COLUMNS = arrayOf(
            COLUMN_ID,
            COLUMN_AUTHOR,
            COLUMN_CONTENT,
            COLUMN_PUBLISHED,
            COLUMN_LIKED_BY_ME,
            COLUMN_LIKES,
            COLUMN_AVATAR,
            COLUMN_VIEWS,
            COLUMN_REPOSTS,
            COLUMN_VIDEO
        )
    }




    override fun getAll(): List<Post> {
        val posts = mutableListOf<Post>()
        db.query(
            PostColumns.TABLE,
            PostColumns.ALL_COLUMNS,
            null,
            null,
            null,
            null,
            "${PostColumns.COLUMN_ID} DESC"
        ).use {
            while (it.moveToNext()) {
                posts.add(map(it))
            }
        }
        return posts
    }

    override fun likeById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun watchById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun shareById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun removeById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun save(post: Post): Post {
        val values = ContentValues().apply {
            // TODO: remove
            put(PostColumns.COLUMN_AUTHOR, "Me")
            put(PostColumns.COLUMN_CONTENT, post.content)
            put(PostColumns.COLUMN_PUBLISHED, "now")
        }
        val id = if (post.id != 0L) {
            db.update(
                PostColumns.TABLE,
                values,
                "${PostColumns.COLUMN_ID} = ?",
                arrayOf(post.id.toString()),
            )
            post.id
        }  else {
            db.insert(PostColumns.TABLE, null, values)
        }
    }
}