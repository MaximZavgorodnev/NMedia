package ru.netology.nmedia.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.EditPostActivity
import ru.netology.nmedia.MainActivity

class ChangedPostContract : ActivityResultContract<String, String?>(){
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, EditPostActivity::class.java)
        return intent.apply { putExtra(Intent.EXTRA_TEXT, input) }
    }


    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return if (resultCode == Activity.RESULT_OK) {
            intent?.getStringExtra(Intent.EXTRA_TEXT)
        } else {
            null
        }
    }


}