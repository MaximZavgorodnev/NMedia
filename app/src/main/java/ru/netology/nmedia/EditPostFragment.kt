package ru.netology.nmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentEditPostBinding
import ru.netology.nmedia.util.LongArg
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

        requireActivity().onBackPressedDispatcher.addCallback(this){
            val text = binding.content.text.toString()
            viewModel.saveRoughCopy(text)
            viewModel.repealEdit()
            findNavController().navigateUp()
        }



        binding.save.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isNotBlank()) {
                viewModel.changeContent(text)
                viewModel.save()
            }
            findNavController().navigate(R.id.action_editPostFragment_to_feedFragment)
        }

        binding.repealEdit.setOnClickListener {
            viewModel.saveRoughCopy("")
            viewModel.repealEdit()
            findNavController().navigateUp()
        }

        return binding.root


    }



    companion object {
        var Bundle.textArg: String? by StringArg
        var Bundle.longArg: Long by LongArg
    }

}