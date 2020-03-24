package com.bellapietra.bellapietra.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bellapietra.bellapietra.databinding.CategoryListItemBinding
import com.bellapietra.bellapietra.network.CategoryItem
import timber.log.Timber

class CategoryAdapter:ListAdapter<CategoryItem,CategoryAdapter.CategoryViewHolder>(CategoryDiffUtilCallBack()) {

    class CategoryViewHolder(val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryItem) {
            binding.categoryItem = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val binding = CategoryListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CategoryViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class CategoryDiffUtilCallBack:DiffUtil.ItemCallback<CategoryItem>(){
    override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.catid == newItem.catid
    }

}