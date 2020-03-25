package com.bellapietra.bellapietra.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bellapietra.bellapietra.databinding.CategoryListItemBinding
import com.bellapietra.bellapietra.network.CategoryItem

class CategoryAdapter(private val clickListener: CategoryClickListener):
    ListAdapter<CategoryItem, CategoryAdapter.CategoryViewHolder>(CategoryDiffUtilCallBack()) {

    class CategoryViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryItem, clickListener: CategoryClickListener) {
            binding.categoryItem = item
            binding.clickListener = clickListener
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
        holder.bind(item,clickListener)
    }
}
class CategoryClickListener(val clickListener:(categoryItem:CategoryItem)->Unit){
    fun onCategoryClick(categoryItem: CategoryItem) = clickListener(categoryItem)
}

class CategoryDiffUtilCallBack : DiffUtil.ItemCallback<CategoryItem>() {
    override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.catid == newItem.catid
    }

}