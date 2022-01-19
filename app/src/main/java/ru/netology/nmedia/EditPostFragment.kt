package ru.netology.nmedia

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.Notification.EXTRA_TEXT
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.fragment_edit_post.view.*
import ru.netology.nmedia.databinding.FragmentEditPostBinding
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel


class EditPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditPostBinding.inflate(inflater, container, false)
        arguments?.textArg?.let { binding.content.setText(it) }


        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)
        binding.save.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isNotBlank()) {
                viewModel.changeContent(text)
                viewModel.save()
            }
            findNavController().navigateUp()
        }
        binding.repealEdit.setOnClickListener {
            viewModel.repealEdit()
//            activity?.setResult(RESULT_OK, activity?.intent)
            findNavController().navigateUp()
        }

        return binding.root
    }

    companion object {
        var Bundle.textArg: String? by StringArg
    }

}