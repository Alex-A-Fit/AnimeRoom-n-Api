package com.example.animelist_real_api.model.apiModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attributes(
    val abbreviatedTitles: List<String> = listOf(""),
    val ageRating: String = "",
    val ageRatingGuide: String = "",
    val averageRating: String = "",
    val canonicalTitle: String = "",
    val coverImage: CoverImage,
    val coverImageTopOffset: Int = 0,
    val createdAt: String = "",
    val description: String = "",
    val episodeLength: Int = 0,
    val favoritesCount: Int = 0,
    val nsfw: Boolean = false,
    val popularityRank: Int = 0,
    val posterImage: PosterImage,
    val ratingRank: Int = 0,
    val showType: String = "",
    val slug: String = "",
    val startDate: String = "",
    val status: String = "",
    val subtype: String = "",
    val synopsis: String = "",
    val titles: Titles,
    val totalLength: Int = 0,
    val updatedAt: String = "",
    val userCount: Int = 0,
    val youtubeVideoId: String = ""
): Parcelable