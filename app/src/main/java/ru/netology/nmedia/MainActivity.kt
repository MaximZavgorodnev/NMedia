package ru.netology.nmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.launch
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_edit_post.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.adapter.AdapterCallback
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.ChangedPostContract
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val newEditPostLauncher = registerForActivityResult(EditPostContract()) { text ->
            text ?: return@registerForActivityResult
                viewModel.changeContent(text.toString())
                viewModel.save()
        }

        val newEditPostLauncher2 = registerForActivityResult(ChangedPostContract()) { text ->
            text ?: return@registerForActivityResult
            viewModel.changeContent(text.toString())
            viewModel.save()
        }

        val adapter = PostAdapter(object : AdapterCallback{
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onWatch(post: Post) {
                viewModel.watchById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply{
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plane"
                }
                val chooser = Intent.createChooser(intent, R.string.chooser_share_post.toString())
                startActivity(chooser)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
                newEditPostLauncher2.launch(post.content)
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



//        viewModel.edited.observe(this){
//            if (it.id != 0L) {
//                if (it.content.isBlank()) {
//
//                }
//            val intent = Intent().apply { putExtra(Intent.EXTRA_TEXT, it.content) }
//            setResult(RESULT_OK, intent)

//                binding.content.setText(it.content)
//                binding.content.requestFocus()
//            }
//        }
//
//        binding.save.setOnClickListener {
//            with (binding.content) {
//                val content = text.toString()
//                if (content.isNullOrBlank()) {
//                    Toast.makeText(it.context, R.string.error_empty_content, Toast.LENGTH_LONG).show()
//                    return@setOnClickListener
//                }
//                viewModel.changeContent(content)
//                viewModel.save()
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(it)
//            }
//            group.visibility = View.GONE
//        }
//
//        binding.repealEdit.setOnClickListener {
//            group.visibility = View.GONE
//            with (binding.content){
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(it)
//            }
//        }

        binding.add.setOnClickListener {
            newEditPostLauncher.launch()
        }

    }
}