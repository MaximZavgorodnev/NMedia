package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
            }

            override fun onPlayVideo(post: Post) {
                if (post.video != null) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = post.video
                    startActivity(intent)
                }
            }

            override fun videoByID(post: Post): Boolean {
                return viewModel.videoByID(post)
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
        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                    newEditPostLauncher2.launch(it.content)
            }
        }

        binding.add.setOnClickListener {
            newEditPostLauncher.launch()
        }
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


    }
}