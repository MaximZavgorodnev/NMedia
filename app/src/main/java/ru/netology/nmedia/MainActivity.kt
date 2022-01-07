package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
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
        val adapterLikes = PostAdapter ({ viewModel.likeById(it.id) } ,
            { viewModel.viewById(it.id) } ,
            { viewModel.repostById(it.id) } ,
             { viewModel.removeById(it.id) })
        binding.container.adapter = adapterLikes
        viewModel.data.observe(this) { posts ->
            adapterLikes.submitList(posts)
        }

        binding.save.setOnClickListener {
            with (binding.content) {
                val content = text.toString()
                if (content.isBlank()) {
                    Toast.makeText(it.context, "ggggggg", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(content)
                viewModel.save()
            }
        }

    }




}