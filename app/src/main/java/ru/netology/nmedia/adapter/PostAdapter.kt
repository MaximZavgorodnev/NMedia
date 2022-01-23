package ru.netology.nmedia.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.util.CorrectNumbers


interface AdapterCallback {
    fun onLike(post: Post)
    fun onWatch(post: Post) // ViewCallback
    fun onShare(post: Post)
    fun onRemove(post: Post)
    fun onEdit(post: Post)
    fun onPlayVideo(post: Post)
    fun onContent(post: Post)
}

class PostAdapter(private val callback: AdapterCallback) :
    ListAdapter<Post, PostViewHolder>(PostDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(private val binding: CardPostBinding,
                     private val callback: AdapterCallback) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(post: Post) {
        binding.apply {
            group.visibility = View.GONE
            author.text = post.author
            published.text = post.published
            content.text = post.content
            avatar.setImageResource(R.drawable.posts_avatars_foreground)
            likes.isChecked = post.likedByMe
            likes.text = CorrectNumbers.correct(post.likes)
            likes.setOnClickListener {
                callback.onLike(post)
            }
            views.isChecked = (post.views > 0)
            views.text = CorrectNumbers.correct(post.views)
            views.setOnClickListener {
                callback.onWatch(post)
            }

            reposts.isChecked = (post.reposts > 0)
            reposts.text = CorrectNumbers.correct(post.reposts)
            reposts.setOnClickListener {
                callback.onShare(post)
            }

//            visible(post)
            group.isVisible = post.video != null
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_post)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> callback.onRemove(post)
                            R.id.edit -> callback.onEdit(post)
                        }
                        true
                    }
                }.show()
            }

            video.setOnClickListener {
                callback.onPlayVideo(post)
            }

            content.setOnClickListener {
                callback.onContent(post)
            }

        }
    }



}

class PostDiffCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
         return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}