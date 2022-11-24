package com.islam.tasks.users.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.islam.tasks.users.databinding.ItemUserBinding
import com.islam.tasks.users.presentation.uimodel.UserUiModel
import com.squareup.picasso.Picasso

class UsersAdapter(private val itemClick: (UserUiModel) -> Unit) :
    ListAdapter<UserUiModel, UsersAdapter.UserViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { itemClick(item) }
    }

    class UserViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {
        fun bind(item: UserUiModel) = with(binding) {
            nameTv.text = item.name
            postsCountTv.text = item.postsCount.toString()
            Picasso.get().load(item.thumbnailUrl).fit().into(userIv)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserUiModel>() {
        override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
            return oldItem == newItem
        }
    }
}