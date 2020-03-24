package com.bellapietra.bellapietra.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bellapietra.bellapietra.databinding.HomeCatgoryListItemBinding
import com.bellapietra.bellapietra.network.Item

class HomeItemAdapter:ListAdapter<Item, HomeItemAdapter.HomeItemViewHolder>(HomeItemDiffUtilCallBack()) {

    class HomeItemViewHolder private constructor(val binding: HomeCatgoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HomeItemViewHolder {
                val binding = HomeCatgoryListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return HomeItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        return HomeItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class HomeItemDiffUtilCallBack:DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

}