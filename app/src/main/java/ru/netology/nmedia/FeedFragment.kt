package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.EditPostFragment.Companion.longArg
import ru.netology.nmedia.EditPostFragment.Companion.textArg
import ru.netology.nmedia.adapter.AdapterCallback
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class FeedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

        val adapter = PostAdapter(object : AdapterCallback {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onWatch(post: Post) {
                viewModel.watchById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply {
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
                arguments?.textArg = post.content
                Bundle().apply {
                    textArg = post.content
                }
                viewModel.edit(post)
            }

            override fun onPlayVideo(post: Post) {
                if (post.video != null) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(post.video)
                    startActivity(intent)
                }
            }

            override fun onContent(post: Post) {
                findNavController().navigate(
                    R.id.action_feedFragment_to_openPostFragment,
                    Bundle().apply { longArg = post.id })
            }
        })

        val id = let { arguments?.longArg }
        id?.let { viewModel.removeById(it) }

        binding.container.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.container.smoothScrollToPosition(0)
                }
            }
        }
        viewModel.edited.observe(viewLifecycleOwner) {
            if (it.id != 0L) {
                findNavController().navigate(
                    R.id.action_feedFragment_to_editPostFragment,
                    Bundle().apply { textArg = it.content })
            }
        }

        binding.add.setOnClickListener {
            val text = viewModel.roughCopy()
//            if (text != "") {

                findNavController().navigate(R.id.action_feedFragment_to_editPostFragment,
                    Bundle().apply { textArg = text })
//            } else
//                findNavController().navigate(R.id.action_feedFragment_to_editPostFragment)
        }


        return binding.root
    }
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



