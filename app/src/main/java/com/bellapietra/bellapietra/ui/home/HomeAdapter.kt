package com.bellapietra.bellapietra.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bellapietra.bellapietra.R
import com.bellapietra.bellapietra.network.Item
import com.bellapietra.bellapietra.network.SingleItems
import com.google.android.material.button.MaterialButton

private var homeItemList: MutableList<SingleItems> = mutableListOf()
class HomeAdapter() :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val homeItemTitle: TextView = itemView.findViewById(R.id.home_item_title)
        private val homeItemRecycler: RecyclerView = itemView.findViewById(R.id.home_item_recycler)
        private val showAllButton:MaterialButton = itemView.findViewById(R.id.show_all_button)

        fun bind(item: SingleItems) {
            val itemList: List<Item> = item.singleItemList!!.toList()
            homeItemTitle.text = itemList[0].catname
            val homeItemAdapter = HomeItemAdapter()
            homeItemRecycler.adapter = homeItemAdapter
            homeItemAdapter.submitList(itemList)

            showAllButton.setOnClickListener {
                it.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToShowAllFragment(
                    null,item,"HomeFragment"
                ))
            }
        }

        companion object {
            fun from(parent: ViewGroup): HomeViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_list_item, parent, false)
                return HomeViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
      return  homeItemList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = homeItemList[position]
        holder.bind(item)
    }

    fun setHomeItemList(list: MutableList<SingleItems>){
        if(list.isNotEmpty()){
            homeItemList.clear()
            homeItemList.addAll(list)
            notifyDataSetChanged()
        }
    }
}