package com.bellapietra.bellapietra.ui.newArrival

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bellapietra.bellapietra.databinding.NewArrivalListItemBinding
import com.bellapietra.bellapietra.network.Item
import timber.log.Timber

class NewArrivalAdapter(val clickListener: NewArrivalClickListener):ListAdapter<Item, NewArrivalAdapter.NewArrivalViewHolder>(NewArrivalDiffUtilCallback()) {

    class NewArrivalViewHolder(val binding: NewArrivalListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item,clickListener: NewArrivalClickListener) {
            binding.item = item
            binding.clicklistener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NewArrivalViewHolder {
                val binding = NewArrivalListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                return NewArrivalViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalViewHolder {
        return NewArrivalViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewArrivalViewHolder, position: Int) {

        val item = getItem(position)
        Timber.e("image url: ${item.imageUrl}")
        holder.bind(item,clickListener)
    }
}

class NewArrivalClickListener(val clickListener:(item:Item)->Unit){
    fun onNewArrivalItemClick(item: Item) = clickListener(item)
}
class NewArrivalDiffUtilCallback:DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

}