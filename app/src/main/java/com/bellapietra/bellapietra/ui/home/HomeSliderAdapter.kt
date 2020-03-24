package com.bellapietra.bellapietra.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import com.bellapietra.bellapietra.R
import com.bellapietra.bellapietra.network.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smarteist.autoimageslider.SliderViewAdapter
import timber.log.Timber

class HomeSliderAdapter(private val imageList: List<Item>) :
    SliderViewAdapter<HomeSliderAdapter.SliderViewHolder>() {

    class SliderViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {

        val sliderIv: ImageView = itemView.findViewById(R.id.slider_image)

        companion object {
            fun from(parent: ViewGroup): SliderViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_slider_item, parent, false)
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
            val url = imageList.get(position).imageUrl
            val uri = url?.toUri()?.buildUpon()?.scheme("https")?.build()
            Glide.with(viewHolder.itemView.context)
                .apply {
                    RequestOptions
                        .overrideOf(600, 200)
                        .centerCrop()
                }
                .load(uri)
                .into(viewHolder.sliderIv)
        }
    }
}