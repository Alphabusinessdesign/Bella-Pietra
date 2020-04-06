package com.bellapietra.bellapietra.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bellapietra.bellapietra.ui.home.apiStatus
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

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
            .apply(
                RequestOptions()
                    .centerCrop()
            )
            .into(iv)
    }
}

@BindingAdapter("homeItemImage")
fun setHomeItemImage(iv: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(iv.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .centerCrop()
            )
            .into(iv)
    }
}

@BindingAdapter("itemImage")
fun setItemImage(iv: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imageUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(iv.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .centerCrop()
            )
            .into(iv)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(layout:ConstraintLayout,status:apiStatus){
    when(status){
        apiStatus.LOADING->{
            layout.visibility = View.VISIBLE
        }
        apiStatus.ERROR->{
            layout.visibility = View.GONE
        }
        apiStatus.DONE->{
            layout.visibility = View.GONE
        }
    }
}

@BindingAdapter("apiStatBottom")
fun bindStatus(layout:BottomNavigationView,status:apiStatus){
    when(status){
        apiStatus.LOADING->{
            layout.visibility = View.GONE
        }
        apiStatus.ERROR->{
            layout.visibility = View.VISIBLE
        }
        apiStatus.DONE->{
            layout.visibility = View.VISIBLE
        }
    }
}

