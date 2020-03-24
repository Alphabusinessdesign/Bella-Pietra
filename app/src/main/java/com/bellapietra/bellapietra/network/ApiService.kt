package com.bellapietra.bellapietra.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://alphabusinessdesigns.com/wordpress/bellaapp/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    //Get Category list
    @GET("catlist.php")
    fun getCategoriesAsync(): Deferred<Category>

    //Get all items
    @GET("iteamslists.php")
    fun getAllItemsAsync():Deferred<Slider>

    //Get item by category
    @POST("catwishlist.php")
    @FormUrlEncoded
    fun getItemByCategoryAsync(@Field("catid")id:Int):Deferred<SingleItems>
}

object BellaApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}