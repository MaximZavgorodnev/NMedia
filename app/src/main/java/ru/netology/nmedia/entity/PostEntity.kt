package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likes: Int,
    val avatar: Int,
    val views: Int = 0,
    val reposts: Int = 0,
    val video: String?) {

    companion object {
        fun fromDto(post: Post) = with(post) {
            PostEntity(id, "Нетологияю Университете интернет-профессийс будущего", content, "23 января 2022 года",
                likedByMe, likes, avatar, views, reposts, video)
        }
    }
}

