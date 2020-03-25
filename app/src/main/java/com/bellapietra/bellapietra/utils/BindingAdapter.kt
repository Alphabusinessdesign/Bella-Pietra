package com.bellapietra.bellapietra.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("categoryName")
fun setCategoryName(tv: TextView, name: String?) {
    name?.let {
        tv.text = name
    }
}

@BindingAdapter("categoryImage")
fun setCategoryImage(iv: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(iv.context)
            .load(imageUri)
            .apply(RequestOptions
                    .overrideOf(200, 600)
                    .circleCrop())
            .into(iv)
    }
}

@BindingAdapter("homeItemImage")
fun setHomeItemImage(iv:ImageView,imgUrl: String?){
    imgUrl?.let {
        val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(iv.context)
            .load(imageUri)
            .apply(RequestOptions()
                .centerCrop())
            .into(iv)
    }
}