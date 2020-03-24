package com.bellapietra.bellapietra.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bellapietra.bellapietra.R
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.home_slider_item.view.*

class HomeSliderAdapter(private val imageList:List<Int>):SliderViewAdapter<HomeSliderAdapter.SliderViewHolder>() {

    class SliderViewHolder(itemView:View):SliderViewAdapter.ViewHolder(itemView){

        val sliderIv:ImageView = itemView.findViewById(R.id.slider_image)
        companion object{
            fun from(parent:ViewGroup):SliderViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_slider_item,parent,false)
                return SliderViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        return SliderViewHolder.from(parent)
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.let {
            val item = getItemPosition(position)
            it.sliderIv.setImageResource(item)
        }
    }
}