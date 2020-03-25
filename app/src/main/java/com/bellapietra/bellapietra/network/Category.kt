package com.bellapietra.bellapietra.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class Category(
    @Json(name = "posts")
    var categoryList: List<CategoryItem>
)

@Parcelize
data class CategoryItem (
    @Json(name = "catid")
    var catid: String?,
    @Json(name = "catname")
    var catname: String?,
    @Json(name = "catimage")
    var catimgUrl:String
):Parcelable