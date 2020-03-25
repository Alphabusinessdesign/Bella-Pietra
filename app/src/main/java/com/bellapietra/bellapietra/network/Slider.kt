package com.bellapietra.bellapietra.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class Slider (
    @Json(name = "posts")
    var itemList: List<Item>?
)

@Parcelize
data class Item (
    @Json(name = "catid")
    var catid: String?,

    @Json(name = "catname")
    var catname: String?,

    @Json(name = "name")
    var name: String?,

    @Json(name = "image_url")
    var imageUrl: String?,

    @Json(name = "description")
    var description: String?
):Parcelable