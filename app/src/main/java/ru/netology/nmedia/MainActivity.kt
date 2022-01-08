package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_post.*
import kotlinx.android.synthetic.main.card_post.view.*
import ru.netology.nmedia.adapter.AdapterCallback

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
        val adapter = PostAdapter(object : AdapterCallback{
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onWatch(post: Post) {
                viewModel.watchById(post.id)
            }

            override fun onRepost(post: Post) {
                viewModel.repostById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

        })


        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.container.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.edited.observe(this){
            group.visibility = View.VISIBLE
            if (it.id != 0L) {
                binding.content.setText(it.content)
                binding.content.requestFocus()
            }
        }

        binding.save.setOnClickListener {
            with (binding.content) {
                val content = text.toString()
                if (content.isBlank()) {
                    Toast.makeText(it.context, R.string.error_empty_content, Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(content)
                viewModel.save()
                setText("")
                clearFocus()
//                AndroidUtils.hideKyboard(it)
            }
        }

    }




}