package com.bellapietra.bellapietra.network

import com.squareup.moshi.Json

data class Slider (
    @Json(name = "posts")
    var itemList: List<Item>?
)

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
)