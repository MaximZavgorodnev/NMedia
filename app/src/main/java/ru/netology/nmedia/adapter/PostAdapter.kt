package ru.netology.nmedia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding


interface AdapterCallback {
    fun onLike(post: Post)
    fun onWatch(post: Post) // ViewCallback
    fun onShare(post: Post)
    fun onRemove(post: Post)
    fun onEdit(post: Post)
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
            author.text = post.author
            published.text = post.published
            content.text = post.content
            avatar.setImageResource(post.avatar)
            likes.isChecked = post.likedByMe
            likes.text = correctNumbers(post.likes)
            likes.setOnClickListener {
                callback.onLike(post)
            }
            views.isChecked = (post.views > 0)
            views.text = correctNumbers(post.views)
            views.setOnClickListener {
                callback.onWatch(post)
            }

            reposts.isChecked = (post.reposts > 0)
            reposts.text = correctNumbers(post.reposts)
            reposts.setOnClickListener {
                callback.onShare(post)
                val intent = Intent().apply{
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plane"
                }
                val chooser = Intent.createChooser(intent, R.string.chooser_share_post.toString())
//                startActivity()

            }

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
        }
    }



    private fun correctNumbers(numberLikes1: Int): String {
        val numberLikes: Double
        val number = when(numberLikes1) {
            in 1000..9_999 -> {
                numberLikes = numberLikes1.toDouble()
                val tisich = (numberLikes/1000).toInt()
                val des = ((numberLikes/1000)%1*10).toInt()
                return "$tisich.$des K"}
            in 10_000..999_999 ->{
                numberLikes = numberLikes1.toDouble()
                val tisich = (numberLikes/1000).toInt()
                return "$tisich K"
            }
            in 1_000_000..9_999_999_999 ->{
                numberLikes = numberLikes1.toDouble()
                val mil = (numberLikes/1000000).toInt()
                val tis = ((numberLikes/1000000)%1*10).toInt()
                return "$mil.$tis M"}
            else -> "$numberLikes1"
        }
        return number
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