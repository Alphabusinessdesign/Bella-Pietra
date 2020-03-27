package com.bellapietra.bellapietra.ui.showAll

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bellapietra.bellapietra.databinding.ShowAllListItemBinding
import com.bellapietra.bellapietra.network.Item

class ShowAllAdapter(val clickListener: ShowAllClickListener):ListAdapter<Item,ShowAllAdapter.ShowAllViewHolder>(ShowAllDiffUtilCallBack()){

    class ShowAllViewHolder(private val binding: ShowAllListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, clickListener: ShowAllClickListener) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ShowAllViewHolder {
                val binding = ShowAllListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ShowAllViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowAllViewHolder {
        return ShowAllViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShowAllViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }
}

class ShowAllClickListener(val clickListener:(item:Item)->Unit){
    fun onShowItemClick(item: Item) = clickListener(item)
}

class ShowAllDiffUtilCallBack:DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

}