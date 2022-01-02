package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетологияю Университете интернет-профессийс будущего",
            content = "Привет? это новая нетология! Нетология – это онлайн университет по обучению интернет-профессиям в области интернет-маркетинга, управления проектами, дизайн и UX, программирование. Нетология ведет свою деятельность на основе государственной лицензии где преподаватели это ведущие эксперты рунета, владелицы компании, известные бизнес тренеры и практики.",
            published = "12 декабря в 13:30",
            likedByMe = false,
            avatar = R.drawable.posts_avatars_foreground,
            viewByMe = false,
            reposts = false
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            avatar.setImageResource(post.avatar)
            var numberLikes = 999
            var numberView = 0
            var numberRepost = 998
            number_of_likes.text = numberLikes.toString()
            number_of_views.text = numberView.toString()
            number_of_reposts.text = numberRepost.toString()
            fun number(liked: Boolean): String {
                if (!liked){
                    return correctNumbers(numberLikes++)
                } else {
                    return correctNumbers(numberLikes--)
                }
            }
            likes.let {
                fun ImageButton.setLiked(liked: Boolean) {
                    val likeIconResId =
                        if (liked) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                    setImageResource(likeIconResId)
                    number_of_likes.text = number(post.likedByMe)
                }
                it.setLiked(post.likedByMe)
                it.setOnClickListener {
                    post.likedByMe = !post.likedByMe
                    likes.setLiked(post.likedByMe)
                }

            }

            views.let {
                fun ImageButton.setView() {
                    val viewIconResId =
                        if (numberView > 0) R.drawable.ic_remove_red_eye_24 else R.drawable.ic_baseline_remove_red_eye_24

                    setImageResource(viewIconResId)
                    number_of_views.text = correctNumbers(numberView)
                }
                it.setView()
                it.setOnClickListener {
                    numberView++
                    post.viewByMe = !post.viewByMe
                    views.setView()
                }

            }

            reposts.let {
                fun ImageButton.setView() {
                    val viewIconResId =
                        if (numberRepost > 0) R.drawable.ic_subdirectory_arrow_right_blue_24 else R.drawable.ic_baseline_subdirectory_arrow_right_24
                    setImageResource(viewIconResId)
                    number_of_reposts.text = correctNumbers(numberRepost)
                }
                it.setView()
                it.setOnClickListener {
                    numberRepost++
                    post.viewByMe = !post.viewByMe
                    reposts.setView()
                }

            }
        }

    }

    fun correctNumbers(numberLikes1: Int): String{
        val numberLikes: Double
        val number = when(numberLikes1) {
            in 1000..9_999 -> {
                numberLikes = numberLikes1.toDouble()
                val tisich = (numberLikes/1000).toInt()
                val des = ((numberLikes/1000)%1*10).toInt()
                return "$tisich.$des K"}
            in 10_000..999_999 ->{
                numberLikes = numberLikes1.toDouble()
                val tisich = (numberLikes/1000).toInt()
                return "$tisich K"
            }
            in 1_000_000..9_999_999_999 ->{
                numberLikes = numberLikes1.toDouble()
                val mil = (numberLikes/1000000).toInt()
                val tis = ((numberLikes/1000000)%1*10).toInt()
                return "$mil.$tis M"}
            else -> "$numberLikes1"
        }
        return number
    }

//    fun adderNumber(view: Boolean, number: Int){
//        if (!view) {
//            number++
//        }
//    }
}