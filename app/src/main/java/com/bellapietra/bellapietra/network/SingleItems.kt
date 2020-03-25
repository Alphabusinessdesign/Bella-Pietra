package com.bellapietra.bellapietra.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SingleItems (
    @Json(name = "posts")
    var singleItemList: List<Item>?
):Parcelable