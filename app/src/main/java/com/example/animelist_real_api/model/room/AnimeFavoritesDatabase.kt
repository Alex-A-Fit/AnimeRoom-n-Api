package com.example.animelist_real_api.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animelist_real_api.model.apiModels.FavoritesPreview

@Database(entities = [FavoritesPreview::class], exportSchema = false, version=2)
abstract class AnimeFavoritesDatabase: RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    companion object {
        const val DB_NAME = "Anime_Favorites_DB"

        fun createDatabaseInstance(context: Context): AnimeFavoritesDatabase{
            return Room.databaseBuilder(context, AnimeFavoritesDatabase::class.java, Companion.DB_NAME)
                .fallbackToDestructiveMigration().build()
        }
    }
}