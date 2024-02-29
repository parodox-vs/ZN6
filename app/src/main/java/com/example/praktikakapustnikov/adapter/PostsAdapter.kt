package com.example.praktikakapustnikov.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikakapustnikov.R
import com.example.praktikakapustnikov.databinding.PostBinding
import com.example.praktikakapustnikov.dto.Post

typealias OnLikeListener = (post: Post) -> Unit
class PostsAdapter(private val onLikeListener: OnLikeListener) : RecyclerView.Adapter<PostViewHolder>() {
    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size
}

class PostViewHolder(
    private val binding: PostBinding,
    private val onLikeListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            textView.text = post.author
            textView2.text = post.published
            textView3.text = post.content
           imageButton.setImageResource(
                    if (post.likedByMe) R.drawable.like_svgrepo_com__1_ else R.drawable.like_svgrepo_com
                )
                if (post.likedByMe) post.likes++
                else {post.likes--}
                textView4?.text = post.likes.toString()

                imageButton2.setOnClickListener {
                    if (post.sharedByMe) post.shares++
                   textView5?.text = post.shares.toString()
            }

        }
    }
}