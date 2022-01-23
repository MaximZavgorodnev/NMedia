package ru.netology.nmedia.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.R

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var  posts = listOf( Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология! Нетология – это онлайн университет по обучению интернет-профессиям в области интернет-маркетинга, управления проектами, дизайн и UX, программирование. Нетология ведет свою деятельность на основе государственной лицензии где преподаватели это ведущие эксперты рунета, владелицы компании, известные бизнес тренеры и практики.",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 0,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Принцип работы ботов-поисковиков музыки такой же как и остальных ботов: пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 10023,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9910,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 1002,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология!",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология!",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология!",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология!",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология!",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология!",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология!",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 999,
            avatar = R.drawable.posts_avatars_foreground,
            views = 0,
            reposts = 998,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 1002,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 1002,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 1002,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 1002,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 1002,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9,
            video = null),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 10023,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 99,
            video = null),
//            Uri.parse("https://www.youtube.com/watch?v=WhWc3b3KhnY")),
        Post(
            id = nextId++,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Пользователь отправляет запрос, в ответ получает файл. Программки загружают треки из профиля ВК в кэш — слушать музыку в приложении сможете без подключения к интернету",
            published = "12 декабря в 13:30",
            likedByMe = false,
            likes = 1002,
            avatar = R.drawable.posts_avatars_foreground,
            views = 467,
            reposts = 9,
            video = null),
    ).reversed()
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe, likes = if (it.likedByMe) it.likes - 1 else it.likes + 1)
        }
        data.value = posts
    }

    override fun watchById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else it.copy(views = it.views + 1)
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else it.copy(reposts = it.reposts + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts


    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(post.copy(id = nextId++, author = "Me", published = "Now")) + posts
        } else {
            posts = posts.map { if (it.id != post.id) it else it.copy(content = post.content) }
        }
        data.value = posts
    }

    override fun saveRoughCopy(text: String) {
        TODO("Not yet implemented")
    }

    override fun getRoughCopy(): String {
        TODO("Not yet implemented")
    }
}