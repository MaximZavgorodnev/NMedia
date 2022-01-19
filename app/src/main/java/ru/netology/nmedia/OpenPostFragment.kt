package ru.netology.nmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nmedia.adapter.PostAdapter
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

        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

        val adapter : PostAdapter




//        binding.postContent.apply {
//            group.visibility = View.GONE
//            author.text = post.author
//            published.text = post.published
//            content.text = post.content
//            avatar.setImageResource(post.avatar)
//            likes.isChecked = post.likedByMe
//            likes.text = CorrectNumbers.correct(post.likes)
//            likes.setOnClickListener {
//                callback.onLike(post)
//            }
//            views.isChecked = (post.views > 0)
//            views.text = CorrectNumbers.correct(post.views)
//            views.setOnClickListener {
//                callback.onWatch(post)
//            }
//
//            reposts.isChecked = (post.reposts > 0)
//            reposts.text = CorrectNumbers.correct(post.reposts)
//            reposts.setOnClickListener {
//                callback.onShare(post)
//            }
//
////            visible(post)
//            group.isVisible = post.video != null
//            menu.setOnClickListener {
//                PopupMenu(it.context, it).apply {
//                    inflate(R.menu.menu_post)
//                    setOnMenuItemClickListener { menuItem ->
//                        when (menuItem.itemId) {
//                            R.id.remove -> callback.onRemove(post)
//                            R.id.edit -> callback.onEdit(post)
//                        }
//                        true
//                    }
//                }.show()
//            }
//
//            video.setOnClickListener {
//                callback.onPlayVideo(post)
//            }
//
////            content.setOnClickListener { group.isVisible = true  }
//
//        }


        return binding.root
    }
}