package com.bellapietra.bellapietra.ui.itemDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bellapietra.bellapietra.databinding.SimilarListItemBinding
import com.bellapietra.bellapietra.network.Item

class SimilarItemAdapter(val clickListener: SimilarItemClickListener):ListAdapter<Item,SimilarItemAdapter.SimilarItemViewHolder>(SimilarItemDiffUtilCallBack()) {

    class SimilarItemViewHolder(val binding: SimilarListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item,clickListener: SimilarItemClickListener) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SimilarItemViewHolder {
                val binding = SimilarListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return SimilarItemViewHolder((binding))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarItemViewHolder {
        return SimilarItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SimilarItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }
}

class SimilarItemClickListener(val clickListener:(item: Item)->Unit){
    fun onSimilarItemClick(item: Item) = clickListener(item)
}

class SimilarItemDiffUtilCallBack : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

}