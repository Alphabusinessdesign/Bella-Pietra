package com.bellapietra.bellapietra.network

import com.squareup.moshi.Json

data class Customer(
    @Json(name = "message")
    val message:String
)