package com.example.animelist_real_api.model.api

import com.example.animelist_real_api.model.apiModels.Data
import com.google.gson.annotations.SerializedName

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    data class AnimeResponse(
        @SerializedName("data")
        val animeList: List<Data>,

//        @SerializedName("meta")
//        val animeMeta: AnimeMeta
    )
    // If we need to create an obj we create an instance of a Data Class

    // data class AnimeMeta(val count: Int)


    @GET("trending/anime")
    suspend fun fetchTrendingAnimes(): Response<AnimeResponse>

    @GET("anime")
    suspend fun fetchAnimes(): Response<AnimeResponse>

    @GET("anime")
    suspend fun fetchAnimeById(@Query("filter[id]") id: String): Response<AnimeResponse>

    companion object {
        val retrofit by lazy {
            Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://kitsu.io/api/edge/")
                .build()
                .create(ApiService::class.java)
        }
    }
}