package ru.netology.nmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityEditPostBinding


class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
    }
}