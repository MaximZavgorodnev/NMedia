package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.R

class PostRepositoryInMemoryImpl : PostRepository {
    var  post = Post(
        id = 1,
        author = "Нетологияю Университете интернет-профессийс будущего",
        content = "Привет? это новая нетология! Нетология – это онлайн университет по обучению интернет-профессиям в области интернет-маркетинга, управления проектами, дизайн и UX, программирование. Нетология ведет свою деятельность на основе государственной лицензии где преподаватели это ведущие эксперты рунета, владелицы компании, известные бизнес тренеры и практики.",
        published = "12 декабря в 13:30",
        likedByMe = false,
        likes = 999,
        avatar = R.drawable.posts_avatars_foreground,
        views = 0,
        reposts = 998
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe,
            likes = if (post.likedByMe) post.likes - 1 else post.likes + 1
        )
        data.value = post
    }

    override fun view() {
        post = post.copy(views = post.views + 1)
        data.value = post
    }

    override fun repost() {
        post = post.copy(reposts = post.reposts + 1)
        data.value = post
    }
}