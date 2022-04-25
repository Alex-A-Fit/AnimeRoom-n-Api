package com.example.animelist_real_api.model.room

import androidx.room.*
import com.example.animelist_real_api.model.apiModels.FavoritesPreview

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteAnime(anime: FavoritesPreview)

    @Delete
    suspend fun deleteFavoriteAnime(anime: FavoritesPreview)

    @Query("SELECT * FROM favorites_preview")
    suspend fun getFavoriteAnime(): List<FavoritesPreview>
}