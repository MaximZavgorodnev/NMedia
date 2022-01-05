package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.card_post.*
import kotlinx.android.synthetic.main.card_post.view.*

import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapterLikes = PostAdapter ({ viewModel.likeById(it.id) } , { viewModel.viewById(it.id) } , { viewModel.repostById(it.id) })
        binding.container.adapter = adapterLikes
        viewModel.data.observe(this) { posts ->
            adapterLikes.submitList(posts)
        }


//            binding.container.removeAllViews()
//            posts.forEach { post->
//                CardPostBinding.inflate(layoutInflater, binding.container, true).apply {
//
//
//                }

//                    views.let {
//                        fun ImageButton.setView() {
//                            val viewIconResId =
//                                if (post.views > 0) R.drawable.ic_remove_red_eye_24 else R.drawable.ic_baseline_remove_red_eye_24
//                            setImageResource(viewIconResId)
//                        }
//                        it.setView()
//                    }
//
//                    reposts.let {
//                        fun ImageButton.setView() {
//                            val viewIconResId =
//                                if (post.reposts > 0) R.drawable.ic_subdirectory_arrow_right_blue_24 else R.drawable.ic_baseline_subdirectory_arrow_right_24
//                            setImageResource(viewIconResId)
//                        }
//                        it.setView()
//                    }
    }




//
//        binding.views.setOnClickListener {
//            viewModel.view()
//        }
//
//        binding.reposts.setOnClickListener {
//            viewModel.repost()
//        }

//    }



}