package com.bellapietra.bellapietra.network

import com.squareup.moshi.Json


data class Category(
    @Json(name = "posts")
    var categoryList: List<CategoryItem>
)

data class CategoryItem (
    @Json(name = "catid")
    var catid: String?,
    @Json(name = "catname")
    var catname: String?,
    @Json(name = "catimage")
    var catimgUrl:String
)