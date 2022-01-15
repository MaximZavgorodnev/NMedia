package ru.netology.nmedia

import android.app.Notification.EXTRA_TEXT
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_edit_post.*
import kotlinx.android.synthetic.main.activity_edit_post.view.*
import ru.netology.nmedia.databinding.ActivityEditPostBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel


class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()

        val text1 = intent.getStringExtra(Intent.EXTRA_TEXT)
        binding.content.setText(text1)

        binding.save.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isBlank()) {
                setResult(RESULT_CANCELED)
            } else {
                val intent = Intent().apply { putExtra(Intent.EXTRA_TEXT, text) }
                setResult(RESULT_OK, intent)
            }
            finish()
        }
        binding.repealEdit.setOnClickListener {
            viewModel.repealEdit()
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}