package ru.netology.nmedia.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.netology.nmedia.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<PostEntity>>


    @Query("""UPDATE PostEntity SET 
                    likes = likes + CASE WHEN likedByMe THEN -1 ELSE 1 END,
                    likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
                    WHERE id =:id""")
    fun likeById(id: Long)

    @Query("""UPDATE PostEntity SET
                    views = views + 1
                    WHERE id =:id""")
    fun watchById(id: Long)

    @Query("""UPDATE PostEntity SET
                    reposts = reposts + 1
                    WHERE id =:id""")
    fun shareById(id: Long)

    @Query("DELETE FROM PostEntity WHERE id =:id")
    fun removeById(id: Long)

    @Query("UPDATE PostEntity SET content =:content WHERE id =:id")
    fun updateContent(id: Long,content: String)
    @Insert
    fun insert(post: PostEntity)
    fun save(post: PostEntity): Unit = if (post.id != 0L) updateContent(post.id, post.content) else insert(post)


}