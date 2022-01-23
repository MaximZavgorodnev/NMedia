package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.EditPostFragment.Companion.longArg
import ru.netology.nmedia.EditPostFragment.Companion.textArg
import ru.netology.nmedia.databinding.FragmentOpenPostBinding
import ru.netology.nmedia.util.CorrectNumbers
import ru.netology.nmedia.viewmodel.PostViewModel

class OpenPostFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOpenPostBinding.inflate(inflater, container, false)
        val id = arguments?.longArg
        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)
        viewModel.data.observe(viewLifecycleOwner) { posts ->
        val post = posts.first { post -> post.id == id }
            binding.postContent.apply {
                group.visibility = View.GONE
                author.text = post.author
                published.text = post.published
                content.text = post.content
                avatar.setImageResource(post.avatar)
                likes.isChecked = post.likedByMe
                likes.text = CorrectNumbers.correct(post.likes)
                likes.setOnClickListener {
                    viewModel.likeById(post.id)
                }
                views.isChecked = (post.views > 0)
                views.text = CorrectNumbers.correct(post.views)
                views.setOnClickListener {
                    viewModel.watchById(post.id)
                }
                reposts.isChecked = (post.reposts > 0)
                reposts.text = CorrectNumbers.correct(post.reposts)
                reposts.setOnClickListener {
                    viewModel.shareById(post.id)
                    val intent = Intent().apply{
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, post.content)
                        type = "text/plane"
                    }
                    val chooser = Intent.createChooser(intent, R.string.chooser_share_post.toString())
                    startActivity(chooser)
                }
                avatar.setImageResource(R.drawable.posts_avatars_foreground)
                group.isVisible = post.video != null
                menu.setOnClickListener {
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.menu_post)
                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.remove -> {
                                    findNavController().navigate(
                                        R.id.action_openPostFragment_to_feedFragment,
                                        Bundle().apply { longArg = post.id })
                                }
                                R.id.edit -> {
                                    viewModel.edit(post)
                                    findNavController().navigate(
                                        R.id.action_openPostFragment_to_editPostFragment,
                                        Bundle().apply { textArg = post.content })
                                }
                            }
                            true
                        }
                    }.show()
                }
                video.setOnClickListener {
                    if (post.video != null) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(post.video)
                        startActivity(intent)
                    }
                }
            }
        }
        return binding.root
    }
}