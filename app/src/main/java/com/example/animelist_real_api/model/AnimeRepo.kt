package com.example.animelist_real_api.model

import com.example.animelist_real_api.model.apiModels.Data
import com.example.animelist_real_api.model.apiModels.FavoritesPreview
import com.example.animelist_real_api.util.Resource

interface AnimeRepo {
    suspend fun getAnimeFromApi(): Resource<List<Data>>?

    suspend fun getTrendingAnimeFromApi(): Resource<List<Data>>?

    suspend fun getAnimeByIdFromApi(animeId: String): Resource<List<Data>>?

    suspend fun addFavoriteAnimeToDb(anime: FavoritesPreview)

    suspend fun deleteFavoriteAnimeToDb(anime: FavoritesPreview)

    suspend fun getFavoriteAnimeFromDb(): Resource<List<FavoritesPreview>>
}