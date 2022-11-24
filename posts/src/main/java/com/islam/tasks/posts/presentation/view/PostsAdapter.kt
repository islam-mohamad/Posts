package com.islam.tasks.posts.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.tasks.posts.databinding.ItemPostBinding
import com.islam.tasks.posts.presentation.uimodel.PostUiModel

class PostsAdapter() :
    ListAdapter<PostUiModel, PostsAdapter.PostViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostUiModel) = with(binding) {
            titleTv.text = item.title
            bodyTv.text = item.body
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PostUiModel>() {
        override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
            return oldItem == newItem
        }
    }
}