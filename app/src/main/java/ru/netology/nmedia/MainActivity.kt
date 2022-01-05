package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                avatar.setImageResource(post.avatar)
                number_of_likes.text = correctNumbers(post.likes)
                number_of_views.text = correctNumbers(post.views)
                number_of_reposts.text = correctNumbers(post.reposts)
                likes.let {
                    fun ImageButton.setLiked(liked: Boolean) {
                        val likeIconResId =
                            if (liked) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                        setImageResource(likeIconResId)
                    }
                    it.setLiked(post.likedByMe)
                }

                views.let {
                    fun ImageButton.setView() {
                        val viewIconResId =
                            if (post.views > 0) R.drawable.ic_remove_red_eye_24 else R.drawable.ic_baseline_remove_red_eye_24
                        setImageResource(viewIconResId)
                    }
                    it.setView()
                }

                reposts.let {
                    fun ImageButton.setView() {
                        val viewIconResId =
                            if (post.reposts > 0) R.drawable.ic_subdirectory_arrow_right_blue_24 else R.drawable.ic_baseline_subdirectory_arrow_right_24
                        setImageResource(viewIconResId)
                    }
                    it.setView()
                }
            }
        }

        binding.likes.setOnClickListener {
            viewModel.like()
        }

        binding.views.setOnClickListener {
            viewModel.view()
        }

        binding.reposts.setOnClickListener {
            viewModel.repost()
        }

    }

    fun correctNumbers(numberLikes1: Int): String {
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
}