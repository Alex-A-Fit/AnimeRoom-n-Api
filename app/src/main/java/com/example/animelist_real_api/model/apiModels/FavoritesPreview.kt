package com.example.animelist_real_api.model.apiModels

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorites_preview")
@Parcelize
data class FavoritesPreview(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val animeId: String = "",
    val animeTitle: String = "",
    val animePosterImg: String = ""
): Parcelable
