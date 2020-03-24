package com.bellapietra.bellapietra.network

import com.squareup.moshi.Json


data class SingleItems (
    @Json(name = "posts")
    var singleItemList: List<Item>?
)