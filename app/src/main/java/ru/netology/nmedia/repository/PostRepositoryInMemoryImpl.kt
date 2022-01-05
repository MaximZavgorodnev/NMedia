package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.R

class PostRepositoryInMemoryImpl : PostRepository {
    var  posts = listOf( Post(
            id = 1,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология! Нетология – это онлайн университет по обучению интернет-профессиям в области интернет-маркетинга, управления проектами, дизайн и UX, программирование. Нетология ведет свою деятельность на основе государственной лицензии где преподаватели это ведущие эксперты рунета, владелицы компании, известные бизнес тренеры и практики.",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998),
        Post(
            id = 2,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Принцип работы ботов-поисковиков музыки такой же как и остальных ботов: пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 10023,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9910),
        Post(
            id = 3,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Принцип работы ботов-поисковиков музыки такой же как и остальных ботов: пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 10023,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 99),
        Post(
            id = 4,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Принцип работы ботов-поисковиков музыки такой же как и остальных ботов: пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 10023,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9),
        Post(
            id = 5,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология! Нетология – это онлайн университет по обучению интернет-профессиям в области интернет-маркетинга, управления проектами, дизайн и UX, программирование. Нетология ведет свою деятельность на основе государственной лицензии где преподаватели это ведущие эксперты рунета, владелицы компании, известные бизнес тренеры и практики.",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998)
    )
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe, likes = if (it.likedByMe) it.likes - 1 else it.likes + 1)
        }
        data.value = posts
    }

    override fun viewById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else it.copy(views = it.views + 1)
        }
        data.value = posts
    }

    override fun repostById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else it.copy(reposts = it.reposts + 1)
        }
        data.value = posts
    }
}